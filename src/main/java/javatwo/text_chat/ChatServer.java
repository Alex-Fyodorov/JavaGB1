package javatwo.text_chat;

import java.util.List;

public interface ChatServer {
    void start();

    void stop();

    void callLogin();

    void callLogout();

    List<String> getHistory();

    void writeHistory(String message);
}