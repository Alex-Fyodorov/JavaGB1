package oop.hw1;

public class StoreApp {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        fillCatalog(catalog);
        User user = new User("pro100User", "1111");
        Basket basket = user.getBasket();

        catalog.printAll();
        System.out.println();

        basket.buyProduct(catalog, "Молоко", 2);
        basket.buyProduct(catalog, "Багет", 1);
        basket.buyProduct(catalog, "Круассан", 4);
        basket.buyProduct(catalog, "Картошка", 2);
        basket.buyProduct(catalog, "Помидоры", 1);
        basket.buyProduct(catalog, "Огурцы", 1);
        basket.buyProduct(catalog, "Сметана", 1);
        basket.printBasket();
//        System.out.println();
//        catalog.printAll();
    }

    public static void fillCatalog(Catalog catalog) {
        catalog.addCategory("Овощи");
        catalog.addCategory("Фрукты");
        catalog.addCategory("Молочные продукты");
        catalog.addCategory("Выпечка");
        catalog.addProduct(new Product("Картошка", 25), "Овощи", 100);
        catalog.addProduct(new Product("Морковь", 35), "Овощи", 100);
        catalog.addProduct(new Product("Капуста", 50), "Овощи", 100);
        catalog.addProduct(new Product("Помидоры", 80), "Овощи", 100);
        catalog.addProduct(new Product("Огурцы", 25), "Овощи", 100);
        catalog.addProduct(new Product("Апельсины", 70), "Фрукты", 100);
        catalog.addProduct(new Product("Яблоки", 25), "Фрукты", 100);
        catalog.addProduct(new Product("Нектарины", 150), "Фрукты", 100);
        catalog.addProduct(new Product("Груши", 80), "Фрукты", 100);
        catalog.addProduct(new Product("Мандарины", 40), "Фрукты", 100);
        catalog.addProduct(new Product("Молоко", 80), "Молочные продукты", 100);
        catalog.addProduct(new Product("Сметана", 65), "Молочные продукты", 100);
        catalog.addProduct(new Product("Сыр", 125), "Молочные продукты", 100);
        catalog.addProduct(new Product("Ряженка", 85), "Молочные продукты", 100);
        catalog.addProduct(new Product("Творог", 90), "Молочные продукты", 100);
        catalog.addProduct(new Product("Батон к чаю", 25), "Выпечка", 100);
        catalog.addProduct(new Product("Бородинский", 35), "Выпечка", 100);
        catalog.addProduct(new Product("Круассан", 45), "Выпечка", 100);
        catalog.addProduct(new Product("Багет", 37), "Выпечка", 100);
        catalog.addProduct(new Product("Лаваш", 20), "Выпечка", 100);
    }
}
