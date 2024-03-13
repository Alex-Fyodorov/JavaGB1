package hw.hw101.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyWriter {
    private final FileWriter writer;
    private final Date date;
    private final SimpleDateFormat formater;

    public MyWriter(String fileName) throws IOException {
        this.writer = new FileWriter(fileName, true);
        this.date = new Date();
        this.formater = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
    }

    public void write(String str) throws IOException {
        writer.write(String.format("Add note at %s: %s\n", formater.format(date.getTime()), str));
        writer.flush();
    }

    public void close() throws IOException {
        writer.close();
    }
}
