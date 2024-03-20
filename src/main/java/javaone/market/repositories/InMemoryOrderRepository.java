package javaone.market.repositories;

import javaone.market.exceptions.QuantityIsNegativeException;
import javaone.market.models.Order;
import javaone.market.models.Product;
import javaone.market.models.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InMemoryOrderRepository implements OrderRepository {
    private final List<Order> orders;

    public InMemoryOrderRepository() {
        orders = new ArrayList<>();
    }

    @Override
    public Order addNewOrder(User user) {
        Order order = new Order(user);
        orders.add(order);
        return order;
    }

    @Override
    public Order changeRatio(Order order, float ratio) {
        order.setRatio(ratio);
        return order;
    }

    @Override
    public void addProductToOrder(Order order, Product product, int quantity)
            throws QuantityIsNegativeException {
        if (quantity < 1) throw new QuantityIsNegativeException("The quantity cannot be less than one.");
        order.getProducts().put(product, quantity);
        BigDecimal sum = order.getSum()
                .add(product.getPrice()
                .multiply(BigDecimal.valueOf(quantity))
                .multiply(BigDecimal.valueOf(order.getRatio())));
        order.setSum(sum);
    }
}
