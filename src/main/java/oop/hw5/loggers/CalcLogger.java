package oop.hw5.loggers;

import java.util.List;

public interface CalcLogger {
    void addNote(String note);
    List<String> getHistory();
}
