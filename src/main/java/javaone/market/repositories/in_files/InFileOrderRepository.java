package javaone.market.repositories.in_files;

import javaone.market.exceptions.OrderNotFoundException;
import javaone.market.exceptions.ProductNotFoundException;
import javaone.market.exceptions.QuantityIsNegativeException;
import javaone.market.models.Order;
import javaone.market.models.Product;
import javaone.market.models.User;
import javaone.market.repositories.interfaces.OrderRepository;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InFileOrderRepository implements OrderRepository {
    private static int count;
    private final FileObjectSerializer serializer;

    public InFileOrderRepository() {
        this.serializer = new FileObjectSerializer();
        count = findMaxCount();
    }

    @Override
    public Order addNewOrder(User user) {
        Order order = new Order(user);
        order.setId(count++);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt", true))) {
            writer.write(serializer.orderToString(order));
            writer.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return order;
    }

    @Override
    public Order changeRatio(Order order, float ratio) {
        order.setRatio(ratio);
        rewriteOrderInFile(order);
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
        rewriteOrderInFile(order);
    }

    @Override
    public Order findById(int orderId) throws OrderNotFoundException {
        Order order = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("orders.txt"))) {
            String str = reader.readLine();
            while (str != null) {
                if (str.startsWith(String.valueOf(orderId))) {
                    order = serializer.stringToOrder(str);
                    break;
                }
                str = reader.readLine();
            }
            if (order == null) {
                throw new OrderNotFoundException(
                        String.format("Order with id: %d not found.", orderId));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("orders.txt"))) {
            String str = reader.readLine();
            while (str != null) {
                orders.add(serializer.stringToOrder(str));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return orders;
    }

    private int findMaxCount() {
        int maxCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("orders.txt"))) {
            String str = reader.readLine();
            while (str != null) {
                String[] arr = str.split("//");
                int orderId = Integer.parseInt(arr[0]);
                if (maxCount < orderId) {
                    maxCount = orderId;
                }
                str = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ++maxCount;
    }

    private void rewriteOrderInFile(Order order) {
        List<String> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("orders.txt"))) {
            String str = reader.readLine();
            while (str != null) {
                if (str.startsWith(String.valueOf(order.getId()))) {
                    str = serializer.orderToString(order);
                    orders.add(str);
                } else orders.add(str + "\n");
                str = reader.readLine();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt", false))) {
            for (String strOfOrders : orders) {
                writer.write(strOfOrders);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
