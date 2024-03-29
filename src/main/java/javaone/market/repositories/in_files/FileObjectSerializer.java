package javaone.market.repositories.in_files;

import javaone.market.models.Order;
import javaone.market.models.Product;
import javaone.market.models.Sex;
import javaone.market.models.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class FileObjectSerializer {

    public String productToString(Product product) {
        return String.format("%d,%s,%s\n",
                product.getId(),
                product.getTitle(),
                product.getPrice().toString());
    }

    public Product stringToProduct(String str) {
        String[] arr = str.strip().split(",");
        Product product = new Product(arr[1], BigDecimal.valueOf(Double.parseDouble(arr[2])));
        product.setId(Integer.parseInt(arr[0]));
        return product;
    }

    public String userToString(User user) {
        return String.format("%d,%s,%s,%d,%d,%s\n",
                user.getId(),
                user.getUsername(),
                user.getSex().equals(Sex.MALE) ? "m" : "f",
                user.getBirthDate().getYear(),
                user.getBirthDate().getDayOfYear(),
                user.getPhone());
    }

    public User stringToUser(String str) {
        String[] arr = str.split(",");
        Sex sex = arr[2].equals("m") ? Sex.MALE : Sex.FEMALE;
        LocalDate birthDate = LocalDate.ofYearDay(Integer.parseInt(arr[3]),
                Integer.parseInt(arr[4]));
        User user =  new User(arr[1], sex, birthDate, arr[5]);
        user.setId(Integer.parseInt(arr[0]));
        return user;
    }

    public String orderToString(Order order) {
        StringBuilder orderString = new StringBuilder();
        orderString.append(order.getId())
                .append("//")
                .append(order.getDate().getYear())
                .append("//")
                .append(order.getDate().getDayOfYear())
                .append("//")
                .append(order.getRatio())
                .append("//")
                .append(order.getSum().toString())
                .append("//")
                .append(userToString(order.getUser()))
                .deleteCharAt(orderString.length() - 1)
                .deleteCharAt(orderString.length() - 1);
        Map<Product, Integer> productsMap = order.getProducts();
        for (Map.Entry<Product, Integer> item : productsMap.entrySet()) {
            orderString.append("//")
                    .append(productToString(item.getKey()))
                    .deleteCharAt(orderString.length() - 1)
                    .deleteCharAt(orderString.length() - 1)
                    .append("//")
                    .append(item.getValue());
        }
        orderString.append("\n");
        return orderString.toString();
    }

    public Order stringToOrder(String str) {
        String[] arr = str.split("//");
        int orderId = Integer.parseInt(arr[0]);
        User user = stringToUser(arr[5]);
        LocalDate date = LocalDate.ofYearDay(Integer.parseInt(arr[1]),
                Integer.parseInt(arr[2]));
        float ratio = Float.parseFloat(arr[3]);
        BigDecimal sum = BigDecimal.valueOf(Double.parseDouble(arr[4]));
        Order order = new Order(orderId, user, date, ratio, sum);

        if (arr.length > 6) {
            for (int i = 6; i < arr.length; i+=2) {
                Product product = stringToProduct(arr[i]);
                if (i + 1 < arr.length) {
                    int quantity = Integer.parseInt(arr[i + 1]);
                    order.getProducts().put(product, quantity);
                }
            }
        }
        return order;
    }
}
