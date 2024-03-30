package javatwo.develop;

public class Backender extends Developer implements Frontendable, Backendable {

    public Backender(String name) {
        super(name);
    }

    @Override
    public void doBack() {
        System.out.println("Good backend work!!!");
    }

    @Override
    public void doFront() {
        System.out.println("Can`t do frontend work.");
    }

    @Override
    public String toString() {
        return "Backender{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
