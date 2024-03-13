package control.converter;

import control.model.Toy;

public interface Convertering {
    int convertQuantity(String str) throws NumberFormatException, RuntimeException;
    Toy convertToy(String str) throws NumberFormatException, RuntimeException;
}
