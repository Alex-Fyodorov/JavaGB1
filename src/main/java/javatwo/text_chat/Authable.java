package javatwo.text_chat;

public interface Authable {
    void login(String str);
    void logout(String str);
    void auth(String login, String password, String ipAddress, int portNumber);
}
