package javathree.hw5.server;

import javathree.hw5.client.Converter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private final Map<String, Guest> clients = new HashMap<>();
    private final Converter converter = new Converter();

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("new client: " + clientSocket);
                Guest guest = new Guest(clientSocket, this);
                String login = guest.getLogin();
                if (clients.containsKey(login) || login.equals("SERVER")) {
                    guest.sendMessage(converter.stringToJson("SERVER",
                            "@ client with that login already exists"));
                    guest.close();
                    continue;
                }
                clients.put(login, guest);
                sendMessageToAll(login, converter.stringToJson(login, "new client entered: " + login));
                guest.sendMessage(converter.stringToJson("SERVER",
                        "@ connection with server, login: " + login));
                guest.setOnCloseHandler(() -> {
                    clients.remove(login);
                    sendMessageToAll(login, converter.stringToJson(login, "client came out: " + login));
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

    public void sendMessageToAll(String login, String message) {
        for(Map.Entry<String, Guest> client : clients.entrySet()) {
            if (!client.getKey().equals(login)) {
                sendMessageToClient(client.getKey(), message);
            }
        }
    }
}
