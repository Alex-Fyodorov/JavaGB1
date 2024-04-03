package javatwo.sem3.task2;

import java.util.Arrays;

public class Collect<T> {

    private T[] arrT;

    public Collect(T... arrT) {
        this.arrT = arrT;
    }

    public void add(T t) {
        for (int i = 0; i < arrT.length; i++) {
            if (arrT[i] == null) {
                arrT[i] = t;
                return;
            }
        }
        arrT = Arrays.copyOf(arrT, arrT.length * 2);
        add(t);
    }

    public void remove(T t) {
        for (int i = 0; i < arrT.length; i++) {
            if (arrT[i] != null && arrT[i].equals(t)) {
                System.arraycopy(arrT, i + 1, arrT, i, arrT.length - 1 - i);
            }
        }
    }

    @Override
    public String toString() {
        return "Collect{" +
                "arrT=" + Arrays.toString(arrT) +
                '}';
    }

    public T[] getArrT() {
        return arrT;
    }
}
