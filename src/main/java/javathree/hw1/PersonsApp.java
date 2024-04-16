package javathree.hw1;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class PersonsApp {

    public static void main(String[] args) {
        List<Department> departments = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            departments.add(new Department("Department #" + i));
        }

        List<Person> persons = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            int randomDepartmentIndex = ThreadLocalRandom.current().nextInt(departments.size());
            Department department = departments.get(randomDepartmentIndex);

            Person person = new Person();
            person.setName("Person #" + i);
            person.setAge(ThreadLocalRandom.current().nextInt(20, 65));
            person.setSalary(ThreadLocalRandom.current().nextInt(20_000, 100_000) * 1.0);
            person.setDepartment(department);

            persons.add(person);
        }

        //persons.forEach(System.out::println);
        //System.out.println(countPersons(persons, 50, 40000));
        //System.out.println(averageSalary(persons, 1));
        //groupByDepartment(persons).entrySet().forEach(System.out::println);
        //maxSalaryByDepartment(persons).entrySet().forEach(System.out::println);
        //System.out.println();
        //maxSalaryByDepartment2(persons).entrySet().forEach(System.out::println);
        //minSalaryPersons(persons).forEach(System.out::println);
        //System.out.println();
        //minSalaryPersons2(persons).forEach(System.out::println);
    }

    /**
     * Найти количество сотрудников, старше x лет с зарплатой больше, чем d
     */
    static int countPersons(List<Person> persons, int x, double d) {
        return (int) persons.stream()
                .filter(p -> p.getAge() > x && p.getSalary() > d)
                .count();
    }

    /**
     * Найти среднюю зарплату сотрудников, которые работают в департаменте X
     */
    static OptionalDouble averageSalary(List<Person> persons, int x) {
        return persons.stream()
                .filter(p -> p.getDepartment().getName().equals("Department #" + x))
                .mapToDouble(Person::getSalary)
                .average();
    }

    /**
     * Сгруппировать сотрудников по департаментам
     */
    static Map<Department, List<Person>> groupByDepartment(List<Person> persons) {
        return persons.stream().collect(Collectors.groupingBy(Person::getDepartment));
    }

    /**
     * Найти максимальные зарплаты по отделам
     */
    static Map<Department, Double> maxSalaryByDepartment(List<Person> persons) {
        // Поизвращался ради интереса, нормальное решение ниже.
        return persons.stream()
                .collect(Collectors.groupingBy(Person::getDepartment))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, p -> p.getValue().stream()
                        .mapToDouble(Person::getSalary)
                        .max()
                        .getAsDouble()));
    }

    static Map<Department, Double> maxSalaryByDepartment2(List<Person> persons) {
        return persons.stream().collect(Collectors.toMap(Person::getDepartment, Person::getSalary, Math::max));
    }

    /**
     * ** Сгруппировать имена сотрудников по департаментам
     */
    static Map<Department, List<String>> groupPersonNamesByDepartment(List<Person> persons) {
        return persons.stream().collect(Collectors.groupingBy(Person::getDepartment,
                Collectors.mapping(Person::getName, Collectors.toList())));
    }

    /**
     * ** Найти сотрудников с минимальными зарплатами в своем отделе
     */
    static List<Person> minSalaryPersons(List<Person> persons) {
        // У этого метода также две реализации. Первую написал сам, вторую по подсказке гугла.
        // Своя нравится больше.
        return persons.stream()
                .collect(Collectors.toMap(Person::getDepartment, p -> p,
                        (t1, t2) -> Comparator.comparingDouble(Person::getSalary)
                                .compare(t1, t2) < 0 ? t1 : t2))
                .values().stream()
                .toList();
    }

    static List<Person> minSalaryPersons2(List<Person> persons) {
        return persons.stream().collect(Collectors.groupingBy(Person::getDepartment,
                Collectors.minBy((t1, t2) -> (int) (t1.getSalary() - t2.getSalary()))))
                .values().stream()
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
