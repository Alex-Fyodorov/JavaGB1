package oop.sem3.task2;

public class FruitsApp {
    public static void main(String[] args) {
        Fruit fruit = Fruit.ORANGE;

        switch (fruit) {
            case APPLE, ORANGE, BANANA, PINEAPPLE, WATERMELLON ->
                    System.out.println(fruit.getRusValue());
            default -> System.out.println("This fruit not found.");
        }
    }
}
