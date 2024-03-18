package oop.hw1;

import java.util.HashMap;
import java.util.Map;

public class Basket {
    private final Map<Product, Integer> productsBasket;

    public Basket() {
        productsBasket = new HashMap<>();
    }

    /**
     * Метод запрашивает продукт и его количество из каталога и добавляет
     * его в корзину.
     * @param catalog Каталог, в который обращается метод.
     * @param title Название продукта.
     * @param quantity Количество продукта.
     */
    public void buyProduct(Catalog catalog, String title, int quantity) {
        Map<Product, Integer> purchase = catalog.getProduct(title, quantity);
        for (Map.Entry<Product, Integer> item : purchase.entrySet()) {
            productsBasket.put(item.getKey(), item.getValue());
        }
    }

    /**
     * Метод распечатывает содержимое корзины, а заодно подсчитывает общую
     * сумму покупки и выводит её в конце всего списка.
     */
    public void printBasket() {
        int sumProducts = 0;
        System.out.println("Корзина:");
        for (Map.Entry<Product, Integer> item : productsBasket.entrySet()) {
            System.out.println(item.getKey() + ", кол-во: " + item.getValue());
            sumProducts += item.getKey().getPrice() * item.getValue();
        }
        System.out.printf("Общая сумма покупки: %d\n", sumProducts);
    }
}
