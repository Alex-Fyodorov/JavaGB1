package javatwo.text_chat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server implements ChatServer {
    private boolean isServerWorking;
    private final Authable auth;

    public Server(Authable auth) {
        this.isServerWorking = false;
        this.auth = auth;
        new ServerWindow(this);
    }

    public boolean isServerWorking() {
        return isServerWorking;
    }

    @Override
    public void start() {
        isServerWorking = true;
    }

    @Override
    public void stop() {
        isServerWorking = false;
    }

    @Override
    public void callLogin() {
        auth.login("Login is correct!");
    }

    @Override
    public void callLogout() {
        auth.logout("Logout from chat!");
    }

    @Override
    public List<String> getHistory() {
        List<String> history = new ArrayList<>();
        long count = countLinesOfHistory();
        if (count > 0) {
            try (BufferedReader reader = new BufferedReader(
                    new FileReader("history.txt"))) {
                count -= 100;
                if(count < 0) count = 0;
                reader.lines()
                        .skip(count)
                        .map(str -> str = str + "\n")
                        .forEach(history::add);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return history;
    }

    private long countLinesOfHistory() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader("history.txt"))) {
            return reader.lines().count();
        } catch (IOException e) {
            return 0;
        }
    }

    @Override
    public void writeHistory(String message) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("history.txt", true))) {
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
