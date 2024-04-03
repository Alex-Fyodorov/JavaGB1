package javatwo.hw3.task4;

public class PairApp {

    public static void main(String[] args) {
        Pair<Integer, String> pair = new Pair<>(111, "Сто одиннадцать");
        System.out.printf("Get first: %d\n", pair.getFirst());
        System.out.printf("Get second: %s\n", pair.getSecond());
        System.out.println();
        System.out.println(pair);
    }
}
