package sem5;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Words {
    public static void main(String[] args) {
        TreeMap<Integer, List<String>> treeMap = sortWords("Мороз и солнце день чудесный " +
                "Еще ты дремлешь друг прелестный Пора красавица проснись");

        System.out.println(treeMap);
    }

    /**
     * Взять набор строк, например,
     *
     * Мороз и солнце день чудесный
     * Еще ты дремлешь друг прелестный
     * Пора красавица проснись.
     *
     * Написать метод, который отсортирует эти строки по длине с помощью TreeMap.
     * Строки с одинаковой длиной не должны “потеряться”.
     */
    public static TreeMap<Integer, List<String>> sortWords(String str) {
        TreeMap<Integer, List<String>> treeMap = new TreeMap<>();
        String[] strArray = str.split(" ");
        for (String item : strArray) {
            int length = item.length();
            if (!treeMap.containsKey(length)) {
                treeMap.put(length, new ArrayList<>());
            }
            treeMap.get(length).add(item);
        }
        return treeMap;
    }

    static void sfdsf() {

    }
}
