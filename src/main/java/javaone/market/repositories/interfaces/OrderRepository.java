package javaone.market.repositories.interfaces;

import javaone.market.exceptions.OrderNotFoundException;
import javaone.market.exceptions.QuantityIsNegativeException;
import javaone.market.models.Order;
import javaone.market.models.Product;
import javaone.market.models.User;

import java.util.List;

public interface OrderRepository {
    Order addNewOrder(User user);
    Order changeRatio(Order order, float ratio);
    void addProductToOrder(Order order, Product product, int quantity) throws QuantityIsNegativeException;
    Order findById(int orderId) throws OrderNotFoundException;
    List<Order> getAll();
}
