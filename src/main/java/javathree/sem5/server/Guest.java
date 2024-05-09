package javathree.sem5.server;

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

    public Guest(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.in = new Scanner(socket.getInputStream());
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.login = in.nextLine();
        this.server = server;
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String messageFromClient = in.nextLine();
                System.out.println(login + ": " + messageFromClient);
                if (messageFromClient.equals("exit")) {
                    System.out.println("client logout");
                    if (onCloseHandler != null) onCloseHandler.run();
                    break;
                }

                if (messageFromClient.startsWith("@")) {
                    String[] split = messageFromClient.split("\\s+");
                    String loginTo = split[0].substring(1);
                    String message = messageFromClient.replace("@" + loginTo + " ", "");
                    server.sendMessageToClient(loginTo, "@" + login + ": " + message);
                } else {
                    server.sendMessageToAll("@" + login + ": " + messageFromClient);
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

    public  void close() throws IOException {
        socket.close();
    }
}
