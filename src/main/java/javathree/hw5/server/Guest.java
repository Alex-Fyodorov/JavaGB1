package javathree.hw5.server;

import javathree.hw5.client.Converter;
import javathree.hw5.client.Message;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Guest implements Runnable {

    private final Socket socket;
    private final Scanner in;
    private final PrintWriter out;
    @Setter
    private Runnable onCloseHandler;
    @Getter
    private final String login;
    private final Server server;
    private final Converter converter;

    public Guest(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.in = new Scanner(socket.getInputStream());
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.login = in.nextLine();
        this.server = server;
        this.converter = new Converter();
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String messageFromClient = in.nextLine();
                Message message = converter.jsonToMessage(messageFromClient);
                System.out.printf("@%s: %s\n", message.getFrom(), message.getMessage());

                if (message.getMessage().equals("exit")) {
                    System.out.println("client logout");
                    break;
                }

                if (message.getAll()) {
                    server.sendMessageToAll(message.getFrom(), messageFromClient);
                } else {
                    server.sendMessageToClient(message.getTo(), messageFromClient);
                }
            }
            try {
                close();
            } catch (IOException e) {
                System.err.println("error: " + e.getMessage());
            }
        } finally {
            if (onCloseHandler != null) {
                onCloseHandler.run();
            }
        }
    }

    public void close() throws IOException {
        socket.close();
    }
}
