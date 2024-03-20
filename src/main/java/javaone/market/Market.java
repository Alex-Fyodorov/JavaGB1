package javaone.market;

import javaone.market.exceptions.ProductNotFoundException;
import javaone.market.exceptions.QuantityIsNegativeException;
import javaone.market.exceptions.UserNotFoundException;
import javaone.market.models.Order;
import javaone.market.models.Product;
import javaone.market.models.User;
import javaone.market.repositories.OrderRepository;
import javaone.market.repositories.ProductRepository;
import javaone.market.repositories.UserRepository;
import javaone.market.utils.DateChecker;
import javaone.market.view.View;

public class Market {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final DateChecker dateChecker;
    private final View view;

    public Market(UserRepository userRepository, ProductRepository productRepository,
                  OrderRepository orderRepository, DateChecker dateChecker, View view) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.dateChecker = dateChecker;
        this.view = view;
    }

    public Order createOrder(String username) {
        User user = null;
        try {
            user = userRepository.findUserByUsername(username);
        } catch (UserNotFoundException e) {
            view.printString(e.getMessage());
            return null;
        }
        Order order = orderRepository.addNewOrder(user);
        float ratio = dateChecker.checkDateOfOrder(order.getDate(), order.getUser());
        return orderRepository.changeRatio(order, ratio);
    }

    public void addProductInOrder(Order order, String title, int quantity) {
        Product product = null;
        try {
            product = productRepository.findProductByTitle(title);
        } catch (ProductNotFoundException e) {
            view.printString(e.getMessage());
        }
        try {
            orderRepository.addProductToOrder(order, product, quantity);
        } catch (QuantityIsNegativeException e) {
            view.printString(e.getMessage());
        }
    }

    public void printUsersAndProducts() {
        view.printList(userRepository.getAll());
        view.printList(productRepository.getAll());
    }
}
