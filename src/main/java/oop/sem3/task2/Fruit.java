package oop.sem3.task2;

public enum Fruit {
    ORANGE("Апельсин"),
    APPLE("Яблоко"),
    BANANA("Банан"),
    PINEAPPLE("Ананас"),
    WATERMELLON("Арбуз");

    private String rusValue;

    Fruit(String rusValue) {
        this.rusValue = rusValue;
    }

    public String getRusValue() {
        return rusValue;
    }
}
