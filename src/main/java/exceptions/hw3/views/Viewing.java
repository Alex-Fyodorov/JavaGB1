package exceptions.hw3.views;

import java.util.List;

public interface Viewing {

    List<String> createNote();

    void printMessage(String str);

    List<String> editData(List<String> data, int index, String field);
}
