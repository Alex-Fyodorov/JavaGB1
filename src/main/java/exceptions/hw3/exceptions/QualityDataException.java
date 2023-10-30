package exceptions.hw3.exceptions;

public class QualityDataException extends NoteException {
    private int index;
    private String field;

    public QualityDataException(String message, int index, String field) {
        super(message);
        this.index = index;
        this.field = field;
    }

    public int getIndex() {
        return index;
    }

    public String getField() {
        return field;
    }
}
