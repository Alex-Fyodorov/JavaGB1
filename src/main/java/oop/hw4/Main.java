package oop.hw4;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.sum(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6))));
        System.out.println(calculator.sum(new ArrayList<>(Arrays.asList(1d, 2d, 3d, 4d, 5d, 6d))));

        System.out.println(calculator.mult(5, 3.1415927));
        System.out.println(calculator.div(5, 3.1415927));

        System.out.println(calculator.toBin(0.14));
        System.out.println(calculator.toBin("0.14"));

        System.out.println(Long.toBinaryString(5));

    }
}
