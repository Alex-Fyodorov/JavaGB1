package javaone.sem5;

import java.io.*;

public class TaskTwo {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("digits.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("text.txt", true));
            String str = reader.readLine();
            while (str != null) {
                str = str.replace('o', ' ');
                writer.write(str + '\n');
                str = reader.readLine();
            }
            writer.flush();
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
