package javaone.market.repositories;

import javaone.market.exceptions.QuantityIsNegativeException;
import javaone.market.models.Order;
import javaone.market.models.Product;
import javaone.market.models.User;

public interface OrderRepository {
    Order addNewOrder(User user);
    Order changeRatio(Order order, float ratio);
    void addProductToOrder(Order order, Product product, int quantity) throws QuantityIsNegativeException;
}
