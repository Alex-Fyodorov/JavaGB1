package javatwo.sem3.task3;

public class Worker implements Person {

    @Override
    public void doWork() {
        System.out.println("to work");
    }

    @Override
    public void haveRest() {
        System.out.println("not rest");
    }
}
