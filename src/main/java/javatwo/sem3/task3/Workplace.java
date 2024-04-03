package javatwo.sem3.task3;

import java.util.List;

public class Workplace<T extends Person> {
    private List<T> list;

    public Workplace(List<T> list) {
        this.list = list;
    }

    public void work() {
        for (T t : list) {
            t.doWork();
        }
    }
}
