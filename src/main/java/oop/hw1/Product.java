package oop.hw1;

import java.util.Objects;

public class Product {
    private final String title;
    private Integer price;
    private String categoryName;

    public Product(String title, Integer price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return title + ",  цена: " + price;
    }

    /**
     * Методы equals и hashCode сравнивают продукты только по одному критерию title,
     * так как предполагается, что цена и принадлежность к той или иной категории
     * могут меняться.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        return title.equals(((Product) o).title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
