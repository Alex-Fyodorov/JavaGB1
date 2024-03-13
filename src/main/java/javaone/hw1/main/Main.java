package hw.hw101.main;

import hw.hw101.reader.MyReader;
import hw.hw101.writer.MyWriter;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        write(args);
        print(read());
    }

    public static List<String> read() {
        List<String> notes = null;
        try {
            MyReader reader = new MyReader("hw.txt");
            notes = reader.read();
            reader.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return notes;
    }

    public static void write(String[] args) {
        try {
            MyWriter writer = new MyWriter("hw.txt");
            for (String str : args) {
                writer.write(str);
            }
            writer.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void print(List<String> notes) {
        for (String str : notes) {
            System.out.println(str);
        }
    }
}
