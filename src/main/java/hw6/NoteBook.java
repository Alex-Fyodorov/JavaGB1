package hw6;

import java.util.Objects;

public class NoteBook {
    private String brand;
    private int ram;
    private int hardDiskCapacity;
    private String os;
    private String color;

    public NoteBook(String brand, int ram, int hardDiskCapacity, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.hardDiskCapacity = hardDiskCapacity;
        this.os = os;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getHardDiskCapacity() {
        return hardDiskCapacity;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "\nbrand: " + brand + '\n' +
                "RAM: " + ram + '\n' +
                "SSD: " + hardDiskCapacity + '\n' +
                "OS: " + os + '\n' +
                "color: " + color + '\n';
    }

    /**
     * Методы equals и hashCode сам не писал, вставил автоподстановкой.
     * Кстати я разобрался (как мне кажется) как работает подстановка множителей в hashCode.
     * Буду знать, спасибо!
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteBook noteBook = (NoteBook) o;
        return ram == noteBook.ram
                && hardDiskCapacity == noteBook.hardDiskCapacity
                && Objects.equals(brand, noteBook.brand)
                && Objects.equals(os, noteBook.os)
                && Objects.equals(color, noteBook.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, ram, hardDiskCapacity, os, color);
    }
}
