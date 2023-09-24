package oop.hw1;

import java.util.*;

public class Catalog {
    private final Map<Product, Integer> productsCatalog;
    private final Map<String, Catalog.Category> categories;

    public Catalog() {
        productsCatalog = new HashMap<>();
        categories = new HashMap<>();
    }

    /**
     * Метод добавляет продукт в каталог.
     * 1. Выполняется проверка, что наименование продукта не пустое.
     * 2. Выполняется проверка, что категория, в которую добавляется продукт существует.
     * 3. Выполняется проверка, что продукт с таким же названием уже не присутствует в каталоге.
     * Если какая-то из проерок не прошла, метод прерывается.
     * Если все проверки пройдены, продукт добавляется в каталог в список productsCatalog,
     * и также добавляется в выбранную категорию.
     * @param product Добавляемый продукт.
     * @param categoryName Категория, в которую добавляется продукт.
     * @param quantity Количество продукта, добавляемое в каталог.
     */
    public void addProduct(Product product, String categoryName, int quantity){
        if (product.getTitle() == null || product.getTitle().isBlank()) {
            System.out.println("Продукт не добавлен в каталог, так как нет наименования продукта.");
            return;
        }
        if (!categories.containsKey(categoryName)) {
            System.out.printf("""
                    Категории с названием %s нет в каталоге.
                    Если вы хотите добавить продукт именно в эту категорию,
                    сначала необходимо её создать.
                    """, categoryName);
            return;
        }
        for (Map.Entry<String, Catalog.Category> category : categories.entrySet()) {
            boolean isPresent = category.getValue().productsCategory.stream()
                    .anyMatch(p -> p.equals(product));
            if (isPresent) {
                System.out.printf("Продукт %s уже находится в категории %s.\n",
                        product.getTitle(), category.getValue().name);
                return;
            }
        }
        categories.get(categoryName).addProductToCategory(product);
        productsCatalog.put(product, quantity);
    }

    /**
     * Метод добавляет новую категорию.
     * Перед добавлением проверяет, что данная категория уже не присутствует в каталоге.
     * Также проверяет, что название новой категории не пустое.
     * @param name Наименование добавляемой категории.
     */
    public void addCategory(String name){
        if (!categories.containsKey(name) && !name.isBlank()) {
            categories.put(name, new Category(name));
        }
    }

    /**
     * Метод выводит в консоль всё сожержимое каталога в формате:
     * - Категория
     * - Продукт этой категории, цена продукта, количество.
     */
    public void printAll() {
        for (Map.Entry<String, Catalog.Category> category : categories.entrySet()) {
            System.out.println(category.getKey());
            for (Product product : category.getValue().productsCategory) {
                System.out.println(product.toString() + ", кол-во: " + productsCatalog.get(product));
            }
        }
    }

    /**
     * Поиск в каталоге объекта Продукт по его наименованию.
     * @param title Наименование продукта.
     * @return Объект Продукт (или его отсутствие) в обёртке Optional.
     */
    public Optional<Product> findProductByTitle(String title) {
        return productsCatalog.keySet().stream()
                .filter(p -> p.getTitle().equals(title)).findFirst();

    }

    /**
     * Метод возвращает пару Продукт-количество.
     * 1. Ищет в каталоге объект Продукт по его названию.
     * 2. Если продукт не найден, метод прерывается.
     * 3. Сравнивает запрашиваемое количество продукта с тем, что есть в каталоге.
     * Если нужное количество есть, возвращает его и на соответствующее число
     * уменьшает количество данного продукта в каталоге.
     * Если запрашиваемое количество превышает то, что есть в каталоге,
     * возвращает то, что есть и удаляет продукт из каталога.
     * @param title Наименование продукта.
     * @param quantity Запрашиваемое количество.
     * @return Мар-пара Продукт-количество.
     */
    public Map<Product, Integer> getProduct(String title, int quantity) {
        Map<Product, Integer> map = new HashMap<>();
        Optional<Product> optionalProduct = findProductByTitle(title);
        if (optionalProduct.isEmpty()) {
            System.out.printf("Продукта %s в каталоге нет. Уточните название продукта.", title);
            return map;
        }
        Product product = optionalProduct.get();
        int allQuantity = productsCatalog.get(product);
        if (quantity < allQuantity){
            map.put(product, quantity);
            productsCatalog.put(product, allQuantity - quantity);
        } else {
            map.put(product, allQuantity);
            productsCatalog.remove(product);
            categories.get(product.getCategoryName()).removeFromCategory(product);
        }
        return map;
    }

    /**
     * Класс Category сделан внутренним,
     * чтобы нельзя было создать категорию вне каталога.
     */
    private class Category {
        private final String name;
        private final Set<Product> productsCategory;

        private Category(String name) {
            this.name = name;
            this.productsCategory = new HashSet<>();
        }

        /**
         * Метод добавляет продукт в категорию,
         * меняет у этого продукта поле categoryName на название категории.
         * @param product Продукт, добавляемый в категорию.
         */
        private void addProductToCategory(Product product) {
            product.setCategoryName(name);
            productsCategory.add(product);
        }

        /**
         * Метод удаляет продукт из категории,
         * меняет у этого продукта поле categoryName на null.
         * @param product Продукт, удаляемый из категории.
         */
        private void removeFromCategory(Product product) {
            if (productsCategory.contains(product)) {
                productsCategory.remove(product);
                product.setCategoryName(null);
            }
        }
    }
}
