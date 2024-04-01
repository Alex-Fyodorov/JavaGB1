package javatwo.sem2.task2;

public class MouseAdapter extends Adapter {
    @Override
    public void listen() {
        System.out.println("I listen you.");
    }

    public static void main(String[] args) {
        new MouseAdapter().listen();
    }
}
