package javaone.market.repositories.in_memory;

import javaone.market.exceptions.ProductNotFoundException;
import javaone.market.models.Product;
import javaone.market.repositories.interfaces.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InMemoryProductRepository implements ProductRepository {
    private final List<Product> products;
    private static int count = 1;

    public InMemoryProductRepository() {
        products = new ArrayList<>();
        addProduct(new Product("Milk", BigDecimal.valueOf(89)));
        addProduct(new Product("Bread", BigDecimal.valueOf(26)));
        addProduct(new Product("Cheese", BigDecimal.valueOf(125)));
        addProduct(new Product("Butter", BigDecimal.valueOf(137)));
        addProduct(new Product("Potato", BigDecimal.valueOf(27)));
        addProduct(new Product("Carrot", BigDecimal.valueOf(35)));
        addProduct(new Product("Cucumber", BigDecimal.valueOf(54)));
    }

    @Override
    public void addProduct(Product product) {
        product.setId(count++);
        products.add(product);
    }

    @Override
    public Product findProductById(Integer productId) throws ProductNotFoundException {
        return products.stream().filter(p -> p.getId().equals(productId)).findFirst()
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Product with id: %d not found.", productId)));
    }

    @Override
    public Product findProductByTitle(String title) throws ProductNotFoundException {
        return products.stream().filter(p -> p.getTitle().equals(title)).findFirst()
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Product with title: %s not found.", title)));
    }

    @Override
    public List<Product> getAll() {
        return products;
    }
}
