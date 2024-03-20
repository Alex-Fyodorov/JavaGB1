package javaone.market.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private static int count = 1;
    private final int id;
    private final User user;
    private final LocalDate date;
    private final Map<Product, Integer> products;
    private float ratio;
    private BigDecimal sum;

    public Order(User user) {
        this.id = count++;
        this.user = user;
        this.date = LocalDate.now();
        products = new HashMap<>();
        this.ratio = 1f;
        this.sum = BigDecimal.ZERO;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getDate() {
        return date;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", date=" + date +
                ", products=" + products +
                ", ratio=" + ratio +
                ", sum=" + sum.setScale(2, RoundingMode.HALF_UP) +
                '}';
    }
}
