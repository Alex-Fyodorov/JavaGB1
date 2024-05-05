package javatwo.sem4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task3 {

//    Создайте телефонный справочник с помощью Map - телефон это ключ, а имя значение
//    Найдите человека с самым маленьким номером телефона
//    Найдите номер телефона человека чье имя самое большое в алфавитном порядке

    public static void main(String[] args) {
        Map<String, String> phones = new HashMap<>();
        phones.put("11111111", "Pamella");
        phones.put("22222222", "James");
        phones.put("33333333", "Tom");
        phones.put("33333334", "Tom");
        phones.put("44444444", "Judy");
        phones.put("55555555", "Alex");

        System.out.println(phones.get(phones.keySet().stream()
                .min((o1, o2) -> {
                    if (o1.length() != o2.length()) return o1.length() - o2.length();
                    return o1.compareTo(o2);
                }).get()));
        String max = phones.values().stream().max(Comparator.naturalOrder()).get();
        System.out.println(phones.entrySet().stream().filter(o -> o.getValue().equals(max))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));
    }
}
