package javaone.sem4.market;

import javaone.sem4.market.exceptions.ProductNotFoundException;
import javaone.sem4.market.exceptions.QuantityIsNegativeException;
import javaone.sem4.market.exceptions.UserNotFoudException;

import java.util.List;

public class TaskTwo {

    public static void main(String[] args) {
        Market market = new Market();
        List<User> users = market.getUsers();
        int orderId1 = 0;
        int orderId2 = 0;
        List<Product> products = market.getProducts();

        buy(market, users.get(2), products.get(1), 15);
        buy(market, users.get(1), products.get(0), 2);

        System.out.println("===========================================");
        try {
            orderId2 = market.createOrder(new User("Alex", 22, "666666"));
        } catch (UserNotFoudException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void buy(Market market, User user, Product product, int quantity) {
        try {
            int orderId = market.createOrder(user);
            market.addProductToOrder(orderId, product, quantity);
            System.out.println(market.getOrders());
        } catch (UserNotFoudException | ProductNotFoundException | QuantityIsNegativeException e) {
            System.out.println(e.getMessage());
        }
    }
}
