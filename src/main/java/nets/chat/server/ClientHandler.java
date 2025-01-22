package nets.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private MyServer myServer;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private String name;

    public String getName() {
        return name;
    }

    public ClientHandler(MyServer myServer, Socket socket) {
        try {
            this.myServer = myServer;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = "";
            this.myServer.getExecutorService().execute(() -> {
                try {
                    authentication();
                    readMessages();
                } catch (IOException io) {
                    io.printStackTrace();
                } finally {
                    closeConnection();
                }
            });
           /** new Thread(() -> {
                try {
                    authentication();
                    readMessages();
                } catch (IOException io) {
                    io.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();*/
        } catch (IOException io) {
            throw new RuntimeException("Проблемы при создании обработчика клиента.");
        }
    }

    public void authentication() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/auth")) {
                String[] parts = str.split("\\s");
                String nick = myServer.getAuthService().getNickByLoginAndPass(parts[1], parts[2]);
                if (nick != null) {
                    if (!myServer.isNickBusy(nick)) {
                        sendMsg("/authOk " + nick);
                        name = nick;
                        myServer.broadcastMsg(name + " зашёл в чат.");
                        myServer.subscribe(this);
                        return;
                    } else {
                        sendMsg("Учётная запись уже используется.");
                    }
                } else {
                    sendMsg("Неверный логин/пароль.");
                }
            }
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void readMessages() throws IOException {
        while (true) {
            String strFromClient = in.readUTF();
            System.out.println("От " + name + ": " + strFromClient);
            if (strFromClient.startsWith("/")) {
                if (strFromClient.equals("/end")) {
                    return;
                }
                if (strFromClient.startsWith("/w")) {
                    String[] tokens = strFromClient.split("\\s");
                    String nick = tokens[1];
                    String msg = strFromClient.substring(4 + nick.length());
                    myServer.sendMsgToClient(this, nick, msg);
                }
                if (strFromClient.startsWith("/renick")) {
                    String[] tokens = strFromClient.split("\\s");
                    String oldName = name;
                    if (myServer.getAuthService().changeNick(name, tokens[1])) {
                        name = tokens[1];
                        myServer.broadcastMsg("Клиент " + oldName + " сменил ник на " + name + ".");
                    } else {
                        myServer.sendMsgToClient(this, name, "Попытка смены ника не удалась.");
                    }
                }
                continue;
            }
            myServer.broadcastMsg(name + ": " + strFromClient);
        }
    }

    public void closeConnection() {
        myServer.unsubscribe(this);
        myServer.broadcastMsg(name + " вышел из чата.");
        try {
            in.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
