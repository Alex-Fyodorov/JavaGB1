package javatwo.hw4;

import java.time.LocalDate;
import java.util.List;

public class Worker {
    private int id;
    private String name;
    private List<String> phones;
    private LocalDate startToWork;

    public Worker(int id, String name, List<String> phones, LocalDate startToWork) {
        this.id = id;
        this.name = name;
        this.phones = phones;
        this.startToWork = startToWork;
    }

    public Worker() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public LocalDate getStartToWork() {
        return startToWork;
    }

    public void setStartToWork(LocalDate startToWork) {
        this.startToWork = startToWork;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phones=" + phones +
                ", startToWork=" + startToWork +
                '}';
    }
}
