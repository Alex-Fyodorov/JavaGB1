package lec1;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriteApp {

    public static void main(String[] args) {
        try (FileWriter fw = new FileWriter("text.txt", true)) {
            fw.write("line 1");
            fw.append("\n");
            fw.append("2");
            fw.append("\n");
            fw.write("line 3");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
