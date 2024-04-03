package javatwo.hw3.task1;

public class Worker implements Person {

    @Override
    public void doWork() {
        System.out.println("I like to work.");
    }

    @Override
    public void haveRest() {
        System.out.println("I don't know how to rest.");
    }
}
