package oop.hw7.services.converters;

import oop.hw7.models.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntConverter implements Convertering<Integer> {

    /**
     * Метод выделяет из запроса пользователя числа в строковом представлении
     * и передаёт их в другой метод, от которого получает списки чисел в формате
     * Integer и объединяет их в один общий список.
     * @param request Экземпляр запроса.
     * @return Список чисел для калькулятора.
     */
    @Override
    public List<Integer> requestToNumbers(Request request) {
        List<Integer> list = new ArrayList<>();
        for (String number : Arrays.asList(request.getNumX(), request.getNumY())) {
            List<Integer> response = stringToNumbers(number);
            if (response != null && !response.isEmpty()) {
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
     * Метод принимает строку и в зависимости от её состава передаёт её в тот
     * или иной метод, который возвращает список чисел в формате Integer.
     * Этот список метод и возвращает. Если строка не подходит ни к одному методу
     * или пустая, возвращается null.
     * @param str Входящая строка.
     * @return Список чисел в формате Integer или null.
     */
    private List<Integer> stringToNumbers(String str) {
        if (str == null) return null;
        str = str.replace(',', '.');
        if (str.contains("/")) return forFraction(str);
        if (isInteger(str.strip())) return forInteger(str.strip());
        if (isDouble(str.strip())) return forDouble(str.strip());
        return null;
    }

    /**
     * Метод принимает строку в которой присутствует символ "/" и возвращает
     * список из чисел, находящихся справа и слева от этого символа при условии,
     * что они Integer, иначе список возвращается пустым.
     * @param str Входящая строка.
     * @return Список чисел в формате Integer.
     */
    private List<Integer> forFraction(String str) {
        List<Integer> list = new ArrayList<>();
        int index = str.indexOf("/");
        if (index == 0 || index == str.length() - 1) {
            return list;
        }
        String a = str.substring(0, index).strip();
        String b = str.substring(index + 1).strip();
        if (isInteger(a) && isInteger(b)) {
            list.add(Integer.parseInt(a));
            list.add(Integer.parseInt(b));
            return list;
        }
        return list;
    }

    /**
     * Метод принимает строку в которой находится целое число и возвращает
     * список из чисел, первое из которых - само число, второе - единица.
     * @param str Входящая строка.
     * @return Список чисел в формате Integer.
     */
    private List<Integer> forInteger(String str) {
        List<Integer> list = new ArrayList<>();
        list.add(Integer.parseInt(str));
        list.add(1);
        return list;
    }

    /**
     * Метод принимает строку в которой находится число формате Double.
     * Метод умножает число на 10 до тех пор пока оно не станет целым.
     * Возвращает список из чисел, первое из которых - полученное целое число,
     * второе - число, на которое необходимо разделить полученное целое число,
     * чтобы получить изначальное.
     * @param str Входящая строка.
     * @return Список чисел в формате Integer.
     */
    private List<Integer> forDouble(String str) {
        List<Integer> list = new ArrayList<>();
        int index = 1;
        double num = Double.parseDouble(str);
        while (num * index != (int) (num * index)) {
            index *= 10;
        }
        list.add((int) (num * index));
        list.add(index);
        return list;
    }

    /**
     * Проверяет, является ли число Double.
     * @param number Входящее число.
     * @return true или false.
     */
    private boolean isDouble(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Проверяет, является ли число Integer.
     * @param number Входящее число.
     * @return true или false.
     */
    private boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
