package nets.chat.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer {
    private final int PORT = 8189;

    private List<ClientHandler> clients;
    private AuthService authService;
    private File file;
    Writer writer;
    private ExecutorService executorService;

    public ExecutorService getExecutorService() { return executorService; }

    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            authService = new DataBaseAuthService();
            authService.start();
            executorService = Executors.newCachedThreadPool();
            clients = new ArrayList<>();
            file = new File("history/totalHistory.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter(file, true));
            while (true) {
                System.out.println("Сервер ожидает подключения.");
                Socket socket = serverSocket.accept();
                System.out.println("Клиент подключился.");
                new ClientHandler(this, socket);
            }
        } catch (IOException io) {
            System.out.println("Ошибка в работе сервера.");
            io.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            if (authService != null) {
                authService.stop();
            }
            executorService.shutdown();
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void broadcastMsg(String msg) {
        try {
            writer.write(msg + "\n");
            writer.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public synchronized void subscribe(ClientHandler o) {
        clients.add(o);
        broadcastClientList();
    }

    public synchronized void unsubscribe(ClientHandler o) {
        clients.remove(o);
        broadcastClientList();
    }

    public synchronized void sendMsgToClient (ClientHandler from, String nickTo, String msg) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nickTo)) {
                o.sendMsg("От " + from.getName() + ": " + msg);
                from.sendMsg("Клиенту " + nickTo + ": " + msg);
                return;
            }
        }
        from.sendMsg("Участника с ником " + nickTo + " нет в чат-комнате.");
    }

    public synchronized void broadcastClientList() {
        StringBuilder sb = new StringBuilder("/clients");
        for (ClientHandler o : clients) {
            sb.append(" " + o.getName());
        }
        broadcastMsg(sb.toString());
    }
}
