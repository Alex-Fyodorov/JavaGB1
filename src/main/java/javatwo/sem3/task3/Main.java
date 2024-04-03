package javatwo.sem3.task3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>(List.of(new Disworker(), new Worker()));
        Club<Person> club = new Club<>(persons);
        Workplace<Person> workplace = new Workplace<>(persons);

        club.rest();
        System.out.println();
        workplace.work();
    }
}
