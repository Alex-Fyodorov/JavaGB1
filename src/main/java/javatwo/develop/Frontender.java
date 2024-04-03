package javatwo.develop;

public class Frontender extends Developer implements Frontendable, Backendable {

    public Frontender(String name) {
        super(name);
    }

    @Override
    public void doBack() {
        System.out.println("Very bed backend work!");
    }

    @Override
    public void doFront() {
        System.out.println("Good frontend work!!!");
    }

    @Override
    public String toString() {
        return "Frontender{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
