package control.controller;

import control.converter.Convertering;
import control.getter.Getting;
import control.model.Toy;
import control.view.Viewing;
import control.writer.Writing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final Viewing view;
    private final Convertering converter;
    private final Getting getter;
    private final Writing writer;
    private final List<Toy> toyList;
    private int toyCount;

    public Controller(Viewing view, Convertering converter, Getting getter, Writing writer) {
        this.view = view;
        this.converter = converter;
        this.getter = getter;
        this.writer = writer;
        this.toyList = new ArrayList<>();
        this.toyCount = 0;
    }

    public void start() {
        int quantity = inputQuantity();
        toFillToyList(quantity);
        getter.init(toyList);
        for (int i = 0; i < 10; i++) {
            try {
                Toy toy = getter.getToy();
                try {
                    writer.writeString(toy.toString() + "\n");
                    view.printMessage(toy.toString());
                } catch (IOException ioException) {
                    view.printMessage(String.format("Запись сохранить не удалось: %s",
                            toy.toString()));
                }
            } catch (RuntimeException e) {
                view.printMessage(e.getMessage());
            }
        }
    }

    private int inputQuantity() {
        String str = view.inputQuantity(null);
        Integer quantity = null;
        while (quantity == null) {
            try {
                quantity = converter.convertQuantity(str);
            } catch (NumberFormatException e) {
                str = view.inputQuantity("Вы ввели не число или не целое число.");
            } catch (RuntimeException e) {
                str = view.inputQuantity(e.getMessage());
            }
        }
        return quantity;
    }

    private Toy inputToy() {
        String str = view.inputToy(null);
        Toy toy = null;
        while (toy == null) {
            try {
                toy = converter.convertToy(str);
            } catch (NumberFormatException e) {
                str = view.inputToy("В качестве частоты выпадения вы ввели не число " +
                        "или не целое число.");
            } catch (RuntimeException e) {
                str = view.inputToy(e.getMessage());
            }
        }
        return toy;
    }

    private void toFillToyList(int quantity) {
        for (int i = 0; i < quantity; i++) {
            Toy toy = inputToy();
            toy.setId(toyCount + 1);
            toyList.add(toy);
            toyCount++;
        }
    }
}
