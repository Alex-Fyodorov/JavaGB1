package javatwo.sem3.task1;

import java.io.InputStream;

public class SimpleClass<T extends Comparable<T>, V extends InputStream, K extends Number> {

    private final T t;
    private final V v;
    private final K k;

    public SimpleClass(T t, V v, K k) {
        this.t = t;
        this.v = v;
        this.k = k;
    }

    public T getT() {
        return t;
    }

    public V getV() {
        return v;
    }

    public K getK() {
        return k;
    }

    public void printClassesNames() {
        System.out.println("T: " + t.getClass().getSimpleName());
        System.out.println("V: " + v.getClass().getSimpleName());
        System.out.println("K: " + k.getClass().getSimpleName());
    }
}
