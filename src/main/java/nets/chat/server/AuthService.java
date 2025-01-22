package nets.chat.server;

public interface AuthService {
    void start();
    void stop();
    String getNickByLoginAndPass (String login, String pass);
    boolean changeNick(String name, String nick);
}
