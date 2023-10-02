package oop.sem4;

public class MainApp {
    public static void main(String[] args) {
        LRUCache<Employee> employers = new LRUCache<>(3);
        employers.addElement(new Employee("Tom1", 10000, "Worker"));
        employers.addElement(new Employee("Tom2", 10000, "Worker"));
        employers.addElement(new Employee("Tom3", 10000, "Worker"));
        employers.addElement(new Employee("Tom4", 10000, "Worker"));

        System.out.println("employers.getElement(0) = " + employers.getElement(0));

        System.out.println("employers.getAllElement() = " + employers.getAllElement());
    }
}
