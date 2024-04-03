package javatwo.develop;

public class Fullstack extends Developer implements Frontendable, Backendable {

    public Fullstack(String name) {
        super(name);
    }

    @Override
    public void doBack() {
        System.out.println("Good backend work!!!");
    }

    @Override
    public void doFront() {
        System.out.println("Good frontend work!!!");
    }
}
