package oop.sem4.task2;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.sum(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6))));
        System.out.println(calculator.sum(new ArrayList<>(Arrays.asList(1d, 2d, 3d, 4d, 5d, 6d))));
    }
}
