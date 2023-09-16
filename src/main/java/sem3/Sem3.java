package sem3;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

public class Sem3 {
    public static void main(String[] args) {
        task2();
    }

    public static void task0() {
        String s1 = "hello";
        String s2 = "hello";
        String s3 = s1;
        String s4 = "h" + "e" + "l" + "l" + "o";
        String s5 = new String("hello");
        String s6 = new String(new char[]{'h', 'e', 'l', 'l', 'o'});

        System.out.println(s1==s2);
    }

    /**
     * Текст задачи:
     * Заполнить список десятью случайными числами.
     * Отсортировать список методом sort() и вывести его на экран.
     */
    public static void task1() {
        Random random = new Random();
        Integer n = random.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(100, 200));
        }
        //list.sort(Integer::compareTo);
        //Collections.sort(list);
        System.out.println(list.toString());

    }


    /**
     * Текст задачи:
     * Создать список типа ArrayList<String>.
     * Поместить в него как строки, так и целые числа.
     * Пройти по списку, найти и удалить целые числа.
     */
    public static void task2() {
        List list = new ArrayList(List.of("afdfa", 34, "sfdgfg", "sfg", 25, 100));
        System.out.println(list);
        //list.removeIf(i -> i.getClass().getSimpleName().equals("Integer"));
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i) instanceof Integer) {
//                list.remove(i);
//                i--;
//            }
//        }
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof Integer) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }

    /**
     * Каталог товаров книжного магазина сохранен в виде двумерного списка
     * List<ArrayList<String>> так, что на 0-й позиции каждого внутреннего
     * списка содержится название жанра, а на остальных позициях - названия книг.
     * Напишите метод для заполнения данной структуры.
     */
}
