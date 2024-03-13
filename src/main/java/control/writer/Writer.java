package control.writer;

import java.io.FileWriter;
import java.io.IOException;

public class Writer implements Writing {

    @Override
    public void writeString(String str) throws IOException {
        try (FileWriter fw = new FileWriter("toys.txt", true)) {
            fw.write(str);
        }
    }
}
