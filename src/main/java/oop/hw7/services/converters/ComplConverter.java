package oop.hw7.services.converters;

import oop.hw7.models.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComplConverter implements Convertering<Double> {

    /**
     * Метод выделяет из запроса пользователя числа в строковом представлении
     * и передаёт их в другой метод, от которого получает списки чисел в формате
     * Double и объединяет их в один общий список.
     * @param request Экземпляр запроса.
     * @return Список чисел для калькулятора.
     */
    @Override
    public List<Double> requestToNumbers(Request request) {
        List<Double> list = new ArrayList<>();
        for (String number : Arrays.asList(request.getNumX(), request.getNumY())) {
            List<Double> response = stringToNumbers(number);
            if (!response.isEmpty()) {
                list.add(response.get(0));
                list.add(response.get(1));
            } else {
                list.add(null);
                list.add(null);
            }
        }
        return list;
    }

    /**
     * Метод занимается переводом строки, в  список чисел.
     * @param str Введённая строка.
     * @return Список чисел в формате Double.
     */
    private List<Double> stringToNumbers(String str) {
        List<Double> list = new ArrayList<>();
        str = str.strip();
        str = str.substring(0, str.length() - 2);
        str = str.replace(',', '.');
        int index = 0;
        StringBuilder sb = new StringBuilder();
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            index = 1;
            sb.append(str.charAt(0));
        }
        for (int i = index; i < str.length(); i++) {
            if ((str.charAt(i) == '-' || str.charAt(i) == '+')) {
                list.add(toDouble(sb.toString().strip()));
                sb = new StringBuilder();
            }
            sb.append(str.charAt(i));
        }
        list.add(toDouble(sb.toString().strip()));
        return list;
    }

    /**
     * Метод переводит строковое представление числа в Double.
     * @param number Вводимая строка.
     * @return Возвращает Double или null, если перевод в число невозможен.
     */
    private Double toDouble(String number) {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
