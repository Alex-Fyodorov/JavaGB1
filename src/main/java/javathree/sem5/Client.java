package javathree.sem5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static final int SERVER_PORT = 8181;

    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        System.out.println("input your login: ");
        String login = console.nextLine();
        Socket clientSocket = new Socket("localhost", SERVER_PORT);
        System.out.println("connection to the server");
        Scanner in = new Scanner(clientSocket.getInputStream());
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        //UUID clientId = UUID.randomUUID();
        out.println(login);

        new Thread(() -> {
            while (true) {
                System.out.println(in.nextLine());
            }
        }).start();

        new Thread(() -> {
            while (true) {
                out.println(console.nextLine());
            }
        }).start();
    }

//    public static Message buildMessage(String loginFrom, String messageFromClient) {
//        if (messageFromClient.startsWith("@")) {
//            String[] split = messageFromClient.strip().split("\\s+");
//            String loginTo = split[0].substring(1);
//            String message = messageFromClient.replace("@" + loginTo + " ", "");
//            return new Message(loginFrom, loginTo, message);
//        } else {
//            return new Message(loginFrom, messageFromClient);
//        }
//    }
}
