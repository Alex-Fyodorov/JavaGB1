package javatwo.hw3.task4;

public class Pair<K, V> {

    private K valueOne;
    private V valueTwo;

    public Pair(K valueOne, V valueTwo) {
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
    }

    public K getFirst() {
        return valueOne;
    }

    public V getSecond() {
        return valueTwo;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "valueOne=" + valueOne +
                ", valueTwo=" + valueTwo +
                '}';
    }
}
