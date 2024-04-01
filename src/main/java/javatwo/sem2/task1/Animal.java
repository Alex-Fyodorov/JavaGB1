package javatwo.sem2.task1;

public class Animal implements Runnable, Eater {

    @Override
    public void eat() {
        System.out.println("I eat.");
    }

    @Override
    public void run() {
        System.out.println("I run.");
    }

    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.eat();
        animal.run();
        animal.sleep();
    }
}
