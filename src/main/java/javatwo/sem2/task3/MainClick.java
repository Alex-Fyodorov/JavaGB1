package javatwo.sem2.task3;

public class MainClick {

    public static void main(String[] args) {
        new Clickable() {
            @Override
            public void click() {
                System.out.println("=============");
            }
        }.click();

        ((Clickable) () -> System.out.println("11111111111111111")).click();
    }
}
