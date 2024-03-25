package javaone.hw3;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Worker implements Comparable<Worker> {
    private String surname;
    private String name;
    private String patronymic;
    private String position;
    private String phone;
    private int salary;
    private LocalDate birthdate;
    private Task task;

    public Worker(String fio, String position, String phone, int salary, LocalDate birthdate) {
        String[] arr = fio.strip().split(" ");
        this.surname = arr[0];
        this.name = arr[1];
        this.patronymic = arr[2];
        this.position = position;
        this.phone = phone;
        this.salary = salary;
        this.birthdate = birthdate;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Task getTask() {
        return task;
    }

    public void assign(Task task) {
        this.task = task;
    }

    public int getAge() {
        if (this.birthdate == null) {
            return -1;
        }
        return difDate(this.birthdate);
    }

    public void setBithdate(LocalDate birthdate) {
        if (difDate(birthdate) >= 14) {
            this.birthdate = birthdate;
        } else {
            this.birthdate = null;
        }
    }

    private int difDate(LocalDate date) {
        return (int) date.until(LocalDate.now(), ChronoUnit.YEARS);
    }

    @Override
    public String toString() {
        return "Worker{" + surname + " " +  name + " " + patronymic + '\'' +
                ", " + position +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                ", age=" + this.getAge() +
                '}';
    }

    @Override
    public int compareTo(Worker o) {
        return this.getAge() - o.getAge();
    }
}
