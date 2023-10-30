package exceptions.hw3.exceptions;

public abstract class NoteException extends RuntimeException {
    public NoteException(String message) {
        super(message);
    }
}
