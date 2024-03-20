package javaone.market;

import javaone.market.models.Order;
import javaone.market.repositories.InMemoryOrderRepository;
import javaone.market.repositories.InMemoryProductRepository;
import javaone.market.repositories.InMemoryUserRepository;
import javaone.market.utils.DateChecker;
import javaone.market.view.ConsoleView;

public class MainApp {

    public static void main(String[] args) {
        Market market = new Market(new InMemoryUserRepository(), new InMemoryProductRepository(),
                new InMemoryOrderRepository(), new DateChecker(), new ConsoleView());

        market.printUsersAndProducts();

        Order order = market.createOrder("Tom");
        market.addProductInOrder(order, "Milk", 1);
        market.addProductInOrder(order, "Bread", 1);
        market.addProductInOrder(order, "Cheese", 1);
        System.out.println(order);
    }
}
