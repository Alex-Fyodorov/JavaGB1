package oop.hw1;

public class User {
    private final String userName;
    private String password;
    private Basket basket;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        basket = new Basket();
    }

    public Basket getBasket() {
        return basket;
    }
}
