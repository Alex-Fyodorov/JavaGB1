package javaone.market.repositories;

import javaone.market.exceptions.ProductNotFoundException;
import javaone.market.models.Product;

import java.util.List;

public interface ProductRepository {
    void addProduct(Product product);
    Product findProductById(Integer productId) throws ProductNotFoundException;
    Product findProductByTitle(String title) throws ProductNotFoundException;
    List<Product> getAll();
}
