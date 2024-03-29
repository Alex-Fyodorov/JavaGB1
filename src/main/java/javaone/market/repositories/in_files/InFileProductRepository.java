package javaone.market.repositories.in_files;

import javaone.market.exceptions.ProductNotFoundException;
import javaone.market.models.Product;
import javaone.market.repositories.in_memory.InMemoryProductRepository;
import javaone.market.repositories.interfaces.ProductRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InFileProductRepository implements ProductRepository {
    private static int count;
    private final FileObjectSerializer serializer;

    public InFileProductRepository() {
        //productListToFile(); // Эта строка используется только в первый раз.
        serializer = new FileObjectSerializer();
        count = findMaxCount();
    }

    @Override
    public void addProduct(Product product) {
        product.setId(count++);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt", true))) {
            writer.write(serializer.productToString(product));
            writer.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public Product findProductById(Integer productId) throws ProductNotFoundException {
        Product product = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String str = reader.readLine();
            while(str != null) {
                String[] arr = str.split(",");
                if (Integer.parseInt(arr[0]) == productId) {
                    product = serializer.stringToProduct(str);
                    break;
                }
                str = reader.readLine();
            }
            if (product == null) throw new ProductNotFoundException(
                    String.format("Product with id: %d not found.", productId));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Product findProductByTitle(String title) throws ProductNotFoundException {
        Product product = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String str = reader.readLine();
            while(str != null) {
                String[] arr = str.split(",");
                if (arr[1].equals(title)) {
                    product = serializer.stringToProduct(str);
                    break;
                }
                str = reader.readLine();
            }
            if (product == null) throw new ProductNotFoundException(
                    String.format("Product with title: %s not found.", title));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String str = reader.readLine();
            while (str != null) {
                products.add(serializer.stringToProduct(str));
                str = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }



    private int findMaxCount() {
        int maxCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String str = reader.readLine();
            while (str != null) {
                String[] arr = str.split(",");
                int productId = Integer.parseInt(arr[0]);
                if (maxCount < productId) {
                    maxCount = productId;
                }
                str = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ++maxCount;
    }

    private void productListToFile() {
        InMemoryProductRepository repository = new InMemoryProductRepository();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt", true))) {
            for (Product product : repository.getAll()) {
                writer.write(serializer.productToString(product));
            }
            writer.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
