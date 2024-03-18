package oop.hw7.models.loggers;

import java.util.ArrayList;
import java.util.List;

public class GeneralLogger implements CalcLogger {

    List<String> history;

    public GeneralLogger() {
        this.history = new ArrayList<>();
    }

    /**
     * Добавляет в историю новую запись.
     * @param note Новая запись в виде строки.
     */
    @Override
    public void addNote(String note) {
        history.add(note);
    }

    /**
     * Выводит все записи, содержащиеся в истории.
     * @return Список записей в строковом формате.
     */
    @Override
    public List<String> getHistory() {
        return history;
    }
}
