package javaone.sem5;

import java.io.File;

public class TaskThree {

    public static void main(String[] args) {
//        File file = new File(new File(".").getPath());
//        fileTree(file, "");
        changeFileName("mexm.mxm");
    }

    public static void fileTree(File file, String buf) {
        System.out.println("" + buf + file.getName());
        File[] dir = file.listFiles();
        if (dir != null) {
            for (File file1 : dir) {
                fileTree(file1, " > " + buf);
            }
        }
    }

    public static void changeFileName(String name) {
        File file = new File(name);
        name = name.replace('m', 't');
        boolean b = file.renameTo(new File(name));
        System.out.println(b);
    }
}
