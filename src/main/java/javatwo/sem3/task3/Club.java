package javatwo.sem3.task3;

import java.util.List;

public class Club<T extends Person> {
    private List<T> list;

    public Club(List<T> list) {
        this.list = list;
    }

    public void rest() {
        for (T t : list) {
            t.haveRest();
        }
    }
}
