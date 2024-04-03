package javatwo.sem3.task3;

public class Disworker implements Person {

    @Override
    public void doWork() {
        System.out.println("not work");
    }

    @Override
    public void haveRest() {
        System.out.println("to rest");
    }
}
