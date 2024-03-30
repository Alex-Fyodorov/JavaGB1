package javatwo.develop;

public abstract class Developer implements Frontendable, Backendable {
    protected int id;
    protected String name;
    private static int count = 1;

    public Developer(String name) {
        this.id = count++;
        this.name = name;
    }
}
