package javaone.sem5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskOne {

    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> digits = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        try {
            FileOutputStream out = new FileOutputStream("digits.txt");
            for (Integer digit : digits) {
                out.write(digit);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Integer> digitsIn = new ArrayList<>();
        try {
            FileInputStream in = new FileInputStream("digits.txt");
            int ch;
            while ((ch = in.read()) != -1) {
                digitsIn.add(ch);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(digitsIn);
    }
}
