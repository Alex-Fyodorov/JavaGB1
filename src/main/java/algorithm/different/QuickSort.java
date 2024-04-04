package algorithm.different;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(random.nextInt(200));
        }
        System.out.println(list);
        System.out.println(quickSort(list));
    }

    public static List<Integer> quickSort(List<Integer> list) {
        if (list.size() <= 1) return list;
        Integer basic = list.get(0);
        List<Integer> left = list.stream().skip(1).filter(x -> x < basic).toList();
        List<Integer> right = list.stream().skip(1).filter(x -> x >= basic).toList();
        List<Integer> result = new ArrayList<>();
        if (!left.isEmpty()) {
            result.addAll(quickSort(left).stream().toList());
        }
        result.add(basic);
        if (!right.isEmpty()) {
            result.addAll(quickSort(right).stream().toList());
        }
        return result;
    }
}
