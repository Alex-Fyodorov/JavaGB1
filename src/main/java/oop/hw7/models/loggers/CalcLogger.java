package oop.hw7.models.loggers;

import java.util.List;


public interface CalcLogger {

    /**
     * Добавляет в историю новую запись.
     * @param note Новая запись в виде строки.
     */
    void addNote(String note);

    /**
     * Выводит все записи, содержащиеся в истории.
     * @return Список записей в строковом формате.
     */
    List<String> getHistory();
}
