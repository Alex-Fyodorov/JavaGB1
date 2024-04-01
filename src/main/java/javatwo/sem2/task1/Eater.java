package javatwo.sem2.task1;

public interface Eater {
    void eat();

    default void sleep() {
        System.out.println("I sleep.");
    }
}
