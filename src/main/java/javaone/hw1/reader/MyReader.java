package hw.hw101.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyReader {
    private final BufferedReader reader;

    public MyReader(String fileName) throws IOException {
        this.reader = new BufferedReader(new FileReader(fileName));
    }

    public List<String> read() throws IOException {
        List<String> notes = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            notes.add(line);
            line = reader.readLine();
        }
        return notes;
    }

    public void close() throws IOException {
        reader.close();
    }
}
