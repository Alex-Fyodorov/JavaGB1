package javatwo.sem3.task1;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            SimpleClass<String, FileInputStream, Integer> sc = new SimpleClass<>(
                    "www", new FileInputStream("digits.txt"), 5);
            sc.printClassesNames();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
