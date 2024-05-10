package javathree.hw5.client;

import java.io.IOException;

public class ClientMain {

    public static final int SERVER_PORT = 8181;

    public static void main(String[] args) {
        try {
            new Client(SERVER_PORT).start();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
