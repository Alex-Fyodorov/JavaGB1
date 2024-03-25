package javaone.hw3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp {

    public static void main(String[] args) {
        List<Worker> workers = new ArrayList<>(List.of(
           new Worker("Горбачёв Михаил Сергеевич",  "помощник комбайнёра",
                   "666666", 200, LocalDate.of(1931, 8, 30)),
           new Worker("Черненко Константин Устинович", " зав. отделом пропаганды и агитации",
                   "555555", 190, LocalDate.of(1911, 9,11)),
           new Worker("Андропов Юрий Владимирович", "матрос",
                   "444444", 180, LocalDate.of(1914, 6, 15)),
           new Worker("Брежнев Леонид Ильич", "землемер-землеустроитель",
                   "333333", 170, LocalDate.of(1906, 12, 19)),
           new Worker("Хрущёв Никита Сергеевич", "инструктор политотдела 9-й Кубанской армии",
                   "222222", 160, LocalDate.of(1894, 4, 15))
        ));

        // Добавляем руководителя.
        workers.add(new Manager("Джугашвили Иосиф Виссарионович", " секретарь ЦК ВКП(б) — КПСС (1934—1953)",
                "111111", 150, LocalDate.of(1878, 12, 18)));

        // Сортировка по возрасту по встроенному компаратору.
        Collections.sort(workers);
        printAll(workers);

        // Распечатка с сортировкой по ЗП ДО повышения.
        printAll(workers.stream().sorted(Comparator.comparingInt(Worker::getSalary)).collect(Collectors.toList()));
        // Повышение зарплаты.
        Manager.raiseSalary(workers, 110, 100);
        // Распечатка с сортировкой по ЗП ПОСЛЕ повышения.
        printAll(workers.stream().sorted(Comparator.comparingInt(Worker::getSalary)).collect(Collectors.toList()));

        // Средние возраст и ЗП.
        System.out.println(averageAge(workers));
        System.out.println(averageSalary(workers));

        // Сортировка по алфавиту.
        workers.sort(Comparator.comparing(Worker::getSurname));
        printAll(workers);

        // Находим в массиве руководителя.
        Manager manager = (Manager) workers.stream().filter(w -> w instanceof Manager).findFirst()
                .orElseThrow(() -> new RuntimeException("Manager not found."));
        System.out.println(manager);
        System.out.println();

        // Выдаём всем работникам задания.
        int index = 1;
        for (Worker worker : workers) {
            if (!(worker instanceof Manager)) {
                manager.assign(worker, new Task(String.format("Task #%d", index++)));
            }
        }
        for (Worker worker : workers) {
            System.out.printf("%s, %s%n", worker.getSurname(), worker.getTask());
        }
    }

    private static void printAll(List<Worker> workers) {
        for (Worker worker : workers) {
            System.out.println(worker);
        }
        System.out.println("================================================");
    }

    private static float averageAge(List<Worker> workers) {
        int sumAge = 0;
        for (Worker worker : workers) {
            sumAge += worker.getAge();
        }
        return (float) sumAge / workers.size();
    }

    private static float averageSalary(List<Worker> workers) {
        int sumSalary = 0;
        for (Worker worker : workers) {
            sumSalary += worker.getSalary();
        }
        return (float) sumSalary / workers.size();
    }
}
