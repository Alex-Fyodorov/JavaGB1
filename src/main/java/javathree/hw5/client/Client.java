package javathree.hw5.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
    public final Converter converter;
    public final Scanner console;
    public final String login;
    public final Socket clientSocket;
    public final Scanner in;
    public final PrintWriter out;

    public Client(int port) throws IOException {
        this.converter = new Converter();
        this.console = new Scanner(System.in);
        System.out.println("input your login: ");
        this.login = console.nextLine();
        this.clientSocket = new Socket("localhost", port);
        System.out.println("connection to the server");
        this.in = new Scanner(clientSocket.getInputStream());
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println(login);
    }

    public void start() {

        Thread threadIn = new Thread(() -> {
            while (true) {
                try {
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                    Message message = converter.jsonToMessage(in.nextLine());
                    System.out.printf("@%s: %s\n", message.getFrom(), message.getMessage());
                } catch (NoSuchElementException e) {
                    //System.err.println("Error: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }

            }
        });

        Thread threadOut = new Thread(() -> {
            while (true) {
                String messageFromConsole = console.nextLine();
                out.println(converter.stringToJson(login, messageFromConsole));
                if (messageFromConsole.equals("exit")) {
                    threadIn.interrupt();
                    try {
                        close();
                    } catch (IOException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                }
            }
        });

        //threadIn.setDaemon(true);
        threadIn.start();
        threadOut.start();
    }

    public void close() throws IOException {
        if (out != null) {
            out.close();
        }
        if (in != null) {
            in.close();
        }
        if (clientSocket != null) {
            clientSocket.close();
        }
        if (console != null) {
            console.close();
        }
    }
}
