package sem3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HW3 {
    public static void main(String[] args) {
        //removeEvenNumbers(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        //analyzeNumbers(new Integer[]{4, 2, 7, 5, 1, 3, 8, 6, 9});
        mergeSort(new int[]{5, 1, 6, 2, 3, 4, 7});
        //System.out.println(1/2);
    }

    /**
     * Напишите функцию removeEvenNumbers, которая принимала бы произвольный
     * список целых чисел, удаляла бы из него четные числа и выводила список без четных чисел.
     */
    public static void removeEvenNumbers(Integer[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer item : arr) {
            if (item % 2 != 0) {
                list.add(item);
            }
        }
        System.out.println(list);
    }

    /**
     * Напишите функцию analyzeNumbers, которая принимает на вход целочисленный список arr и:
     * Сортирует его по возрастанию и выводит на экран
     * Находит минимальное значение в списке и выводит на экран - Minimum is {число}
     * Находит максимальное значение в списке и выводит на экран - Maximum is {число}
     * Находит среднее арифметическое значений списка и выводит на экран - Average is =  {число}
     */

    public static void analyzeNumbers(Integer[] arr) {
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("Minimum is " + arr[0]);
        System.out.println("Maximum is " + arr[arr.length - 1]);
        Integer sum = 0;
        for (Integer item : arr) {
            sum += item;
        }
        System.out.println("Average is = " + (sum / arr.length));
    }

    /**
     * Внутри класса MergeSort напишите метод mergeSort,
     * который принимает массив целых чисел a и реализует алгоритм
     * сортировки слиянием. Метод должен возвращать отсортированный массив.
     */

    public static int[] mergeSort(int[] a) {
        int[] b[] = new int[(a.length / 2) + (a.length % 2)][];
        for (int i = 1; i < a.length; i += 2) {
            if (a.length - 1 - i == 1) {
                b[(i + 1) / 2] = new int[]{a[i + 1]};
            }
            b[i / 2] = new int[2];
            b[i / 2][0] = a[i - 1];
            b[i / 2][1] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            if (b[i].length > 1 && b[i][1] < b[i][0]) {
                int temp = b[i][1];
                b[i][1] = b[i][0];
                b[i][0] = temp;
            }
        }
        while (b.length > 1) {
            int[] c[] = new int[(b.length / 2) + (b.length % 2)][];
            for (int i = 1; i < b.length; i += 2) {
                if (b.length - 1 - i == 1) {
                    c[(i + 1) / 2] = b[i + 1];
                }
                int index1 = 0, index2 = 0, c1 = 0, c2 = 0;
                int size = b[i].length + b[i - 1].length;
                c[i / 2] = new int[size];
                for (int j = 0; j < size; j++) {
                    if (index1 < b[i - 1].length) {
                        c1 = b[i - 1][index1];
                    } else {
                        c1 = Integer.MAX_VALUE;
                    }
                    if (index2 < b[i].length) {
                        c2 = b[i][index2];
                    } else {
                        c2 = Integer.MAX_VALUE;
                    }
                    if (c1 < c2) {
                        c[i / 2][j] = c1;
                        index1++;
                    } else {
                        c[i / 2][j] = c2;
                        index2++;
                    }
                }

            }
            b = c;
        }
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b[0]));
        return b[0];
    }
}
