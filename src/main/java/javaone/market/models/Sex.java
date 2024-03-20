package javaone.market.models;

public enum Sex {
    MALE ("male"),
    FEMALE ("female");

    private final String value;

    Sex(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
