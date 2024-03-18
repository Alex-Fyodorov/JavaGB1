package exceptions.hw3.exceptions;

public class QuantityDataInputException extends NoteException {

    private int quantity;

    public QuantityDataInputException(String message, int quantity) {
        super(message);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
