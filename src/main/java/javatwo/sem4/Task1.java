package javatwo.sem4;

import java.util.*;

public class Task1 {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("Alex", "Judy", "Tom", "James", "Pamella"));
        System.out.println(names);
        Collections.sort(names);
        System.out.println(names);
        names.sort(Comparator.comparingInt(String::length));
        System.out.println(names);
        Collections.reverse(names);
        System.out.println(names);
    }
}
