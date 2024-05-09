package javathree.sem5.server;

public class ServerMain {

    public static final int SERVER_PORT = 8181;

    public static void main(String[] args) {
        new Server().start(SERVER_PORT);
    }
}
