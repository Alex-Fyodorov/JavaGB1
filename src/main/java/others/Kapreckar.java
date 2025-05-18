package others;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Kapreckar {

    // Программа, иллюстрирующая закон Капрекара.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        cycle(str);
    }

    private static void cycle(String str) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " - " + str);
            str = calculate(str);
        }
    }

    private static String calculate(String str) {
        String[] arr = str.split("");
        List<Integer> list = Arrays.stream(arr).map(Integer::parseInt).toList();
        Integer min = list.stream().sorted().reduce((i1, i2) -> i1 * 10 + i2).get();
        Integer max = list.stream().sorted((i1, i2) -> i2 - i1).reduce((i1, i2) -> i1 * 10 + i2).get();
        String result = String.valueOf(max - min);
        while (result.length() < 4) {
            result = "0".concat(result);
        }
        return result;
    }
}
