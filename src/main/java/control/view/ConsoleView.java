package control.view;

import java.util.Scanner;

public class ConsoleView implements Viewing {

    Scanner scanner = new Scanner(System.in);

    @Override
    public String inputQuantity(String message) {
        if (message != null) {
            System.out.println(message);
            System.out.print("Введите количество игрушек заново: ");
        } else System.out.print("Введите количество игрушек: ");
        String str = scanner.nextLine();
        System.out.println();
        return str;
    }

    @Override
    public String inputToy(String message) {
        if (message != null) {
            System.out.println(message);
            System.out.print("Введите данные этой игрушки заново: ");
        } else System.out.print("Введите название и частоту выпадения игрушки через пробел,\n" +
                "(Пример: машинка 2): ");
        String str = scanner.nextLine();
        System.out.println();
        return str;
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
}
