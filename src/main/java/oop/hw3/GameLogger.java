package oop.hw3;

import java.util.ArrayList;
import java.util.List;

public class GameLogger {
    private List<String> history;

    public GameLogger() {
        history = new ArrayList<>();
    }

    public List<String> getHistory() {
        return history;
    }

    public void addNote(String note) {
        history.add(note);
    }
}
