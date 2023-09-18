package hw5;

import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook {
    private Map<String, List<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    public void addNote(String name, String number) {
        if (name == null || number == null) {
            System.out.println("Имя или номер не введены.");
            return;
        }
        if (!phoneBook.containsKey(name)) {
            phoneBook.put(name, new ArrayList<>());
        }
        phoneBook.get(name).add(number);
    }

    public String findKey(String number) {
        for (Map.Entry<String, List<String>> entry : phoneBook.entrySet()) {
            for (String item : entry.getValue()) {
                if (item.equals(number)) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    public void printOne(String number) {
        String name = findKey(number);
        if (name == null) {
            System.out.printf("Номер не найден: %s\n", number);
        } else {
            System.out.printf("%s: ", name);
            System.out.println(phoneBook.get(name));
        }
        System.out.println();
    }

    public void deleteNumber(String number) {
        String name = findKey(number);
        if (name == null) {
            System.out.printf("Номер не найден: %s\n", number);
            System.out.println();
            return;
        }
        if (phoneBook.get(name).size() == 1) {
            phoneBook.remove(name);
        } else {
            phoneBook.get(name).removeIf(p -> p.equals(number));
        }
    }

    public void printAll() {
        List<String> names = phoneBook.entrySet().stream()
                .sorted(Comparator.comparingInt(o -> o.getValue().size()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        for (String name : names) {
            System.out.printf("%s: ", name);
            System.out.println(phoneBook.get(name));
        }
        System.out.println();
    }
}
