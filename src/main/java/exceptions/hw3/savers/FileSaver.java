package exceptions.hw3.savers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileSaver implements Saving {

    @Override
    public String saveData(List<String> data) {
        String str = createFinalString(data);
        String fileName = data.get(0).toLowerCase() + ".txt";
        String response;
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(str);
            response = "Запись успешно сохранена";
        } catch (IOException e) {
            response = "Запись сохранить не удалось.";
        }
        return response;
    }

    private String createFinalString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) sb.append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }
}
