package javathree.sem5.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private final Map<String, Guest> clients = new HashMap<>();

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("new client: " + clientSocket);
                Guest guest = new Guest(clientSocket, this);
                String login = guest.getLogin();
                if (clients.containsKey(login)) {
                    guest.sendMessage("client with that id already exists");
                    guest.close();
                    continue;
                }
                clients.put(login, guest);
                sendMessageToAll("new client entered: " + login);
                guest.sendMessage("connection with server, login: " + login);
                guest.setOnCloseHandler(() -> {
                    clients.remove(login);
                    sendMessageToAll("client came out: " + login);
                });
                new Thread(guest).start();
            }
        } catch (IOException e) {
            System.err.println("Error: " + port + ": " + e.getMessage());
        }
    }

    public void sendMessageToClient(String login, String message) {
        if (clients.containsKey(login)) {
            Guest guest = clients.get(login);
            guest.sendMessage(message);
        }
    }

    public void sendMessageToAll(String message) {
        clients.values().forEach(it -> sendMessageToClient(it.getLogin(), message));
    }
}
