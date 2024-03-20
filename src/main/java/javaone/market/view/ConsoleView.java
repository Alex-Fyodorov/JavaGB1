package javaone.market.view;

import java.util.List;

public class ConsoleView implements View {
    @Override
    public void printString(String str) {
        System.out.println(str);
        breakLine();
    }

    @Override
    public void printList(List<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
        breakLine();
    }

    private void breakLine() {
        System.out.println("===============================================");
    }
}
