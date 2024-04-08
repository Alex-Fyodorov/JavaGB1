package javatwo.sem4;

import java.util.*;

public class Task2 {

//    Создайте коллекцию мужских и женских имен с помощью интерфейса List - добавьте повторяющиеся значения
//    Получите уникальный список Set на основании List
//    Определите наименьший элемент (алфавитный порядок)
//    Определите наибольший элемент (по количеству букв в слове но в обратном порядке)
//    Удалите все элементы содержащие букву ‘A’

    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList(
                "Judy", "Tom", "Alex", "James", "Pamella", "Judy", "Tom", "James", "Pamella"));
        System.out.println(names);
        Set<String> nameSet = new HashSet<>(names);
        System.out.println(nameSet);
        System.out.println(Collections.min(nameSet));
        System.out.println(Collections.max(nameSet, Comparator.comparingInt(String::length)));
        nameSet.removeIf(n -> n.toLowerCase().contains("a"));
        System.out.println(nameSet);
    }
}
