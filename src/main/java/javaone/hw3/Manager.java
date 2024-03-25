package javaone.hw3;

import java.time.LocalDate;
import java.util.List;

public class Manager extends Worker {

    public Manager(String fio, String position, String phone, int salary, LocalDate birthdate) {
        super(fio, position, phone, salary, birthdate);
    }

    public static void raiseSalary(List<Worker> workers, int age, int addSalary) {
        for (Worker worker : workers) {
            if (worker.getAge() >= age && !(worker instanceof Manager)) {
                worker.setSalary(worker.getSalary() + addSalary);
            }
        }
    }

    public void assign(Worker worker, Task task) {
        worker.assign(task);
    }
}
