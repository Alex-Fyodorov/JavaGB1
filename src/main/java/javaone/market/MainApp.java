package javaone.market;

import javaone.market.models.Order;
import javaone.market.repositories.in_files.InFileOrderRepository;
import javaone.market.repositories.in_files.InFileProductRepository;
import javaone.market.repositories.in_files.InFileUserRepository;
import javaone.market.utils.DateChecker;
import javaone.market.view.ConsoleView;

public class MainApp {

    public static void main(String[] args) {
        Market market = new Market(new InFileUserRepository(), new InFileProductRepository(),
                new InFileOrderRepository(), new DateChecker(), new ConsoleView());

        market.printUsersAndProducts();

        Order order = market.createOrder("Tom");
        market.addProductInOrder(order, "Milk", 1);
        market.addProductInOrder(order, "Bread", 1);
        market.addProductInOrder(order, "Cheese", 1);
        int orderId1 = order.getId();

        order = market.createOrder("Jim");
        market.addProductInOrder(order, "Butter", 1);
        market.addProductInOrder(order, "Bread", 1);
        market.addProductInOrder(order, "Potato", 2);
        int orderId2 = order.getId();

        System.out.println(market.findOrderById(orderId1));
        System.out.println("===============================================");
        System.out.println(market.findOrderById(orderId2));
    }
}
