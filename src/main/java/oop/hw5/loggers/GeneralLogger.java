package oop.hw5.loggers;

import java.util.ArrayList;
import java.util.List;

public class GeneralLogger implements CalcLogger {

    List<String> history;

    public GeneralLogger() {
        this.history = new ArrayList<>();
    }

    @Override
    public void addNote(String note) {
        history.add(note);
    }

    @Override
    public List<String> getHistory() {
        return history;
    }
}
