package control.converter;

import control.model.Toy;

import java.util.Arrays;
import java.util.List;

public class Converter implements Convertering {

    @Override
    public int convertQuantity(String str) throws NumberFormatException, RuntimeException {
        int quantity = Integer.parseInt(str);
        if (quantity <= 0) throw new RuntimeException("Вы ввели ноль или отрицательное число.");
        return quantity;
    }

    @Override
    public Toy convertToy(String str) throws NumberFormatException, RuntimeException {
        List<String> list = Arrays.asList(str.strip().split(" "));
        if (list.size() != 2) {
            throw new RuntimeException("Вы ввели больше или меньше данных, чем необходимо.");
        }
        int weight = Integer.parseInt(list.get(1));
        if (weight <= 0) {
            throw new RuntimeException("В качестве частоты выпадения вы ввели ноль или " +
                    "отрицательное число.");
        }
        return new Toy(0, weight, list.get(0));
    }
}
