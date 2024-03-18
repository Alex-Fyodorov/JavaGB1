package oop.hw7.views;

import oop.hw7.models.Request;

import java.util.List;

public interface Viewing {

    /**
     * Метод помогает пользователю создать изначальный запрос.
     * @return Изначальный запрос от пользователя.
     */
    Request createRequest();

    /**
     * Вывод сообщения об ошибке.
     */
    void printError();

    /**
     * Распечатка ответа на запрос.
     * @param str Строка с ответом.
     */
    void printResponse(String str);

    /**
     * Распечатка истории запросов.
     * @param history Список строк, содержащих информацию о запросе и ответе на него.
     */
    void printHistory(List<String> history);
}
