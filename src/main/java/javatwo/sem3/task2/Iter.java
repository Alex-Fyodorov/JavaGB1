package javatwo.sem3.task2;

public class Iter<T> {
    private final T[] val;
    private int index;

    public Iter(T[] val) {
        this.val = val;
        index = 0;
    }

    public boolean hasNext() {
        return index < val.length && val[index] != null;
    }

    public T next() {
        return val[index++];
    }
}
