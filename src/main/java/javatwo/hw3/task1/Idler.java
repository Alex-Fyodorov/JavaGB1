package javatwo.hw3.task1;

public class Idler implements Person {

    @Override
    public void doWork() {
        System.out.println("I hate to work.");
    }

    @Override
    public void haveRest() {
        System.out.println("I like to rest");
    }
}
