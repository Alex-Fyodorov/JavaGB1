package exceptions.hw3.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleView implements Viewing {

    Scanner scanner = new Scanner(System.in);

    @Override
    public List<String> createNote() {
        System.out.println("Введите через пробел свои данные в формате:\n" +
                "Фамилия Имя Отчество датарождения(дд.мм.гггг) номертелефона(без пробелов и разделителей) пол(м/ж)");
        String note;
        note = scanner.nextLine();
        System.out.println();
        return new ArrayList<>(Arrays.asList(note.strip().split(" ")));
    }

    @Override
    public void printMessage(String str) {
        System.out.println(str);
        System.out.println();
    }

    @Override
    public List<String> editData(List<String> data, int index, String field) {
        System.out.printf("Обнаружена ошибка ввода.\nВведите, пожалуйста, поле \"%s\" заново.\n", field);
        data.set(index, scanner.nextLine());
        System.out.println();
        return data;
    }
}
