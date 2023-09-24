package api.lec1;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileReadApp {
    public static void main(String[] args) {
        try {
            String s = System.getProperty("user.dir").concat("/text.txt");
            BufferedReader br = new BufferedReader(new FileReader(s));
            String str;
            while ((str = br.readLine()) != null) {
                System.out.printf("== %s ==\n", str);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
