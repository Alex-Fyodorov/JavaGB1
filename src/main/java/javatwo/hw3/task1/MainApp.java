package javatwo.hw3.task1;

public class MainApp {
    public static void main(String[] args) {
        Person[] persons = new Person[]{new Idler(), new Worker()};
        Club<Person> club = new Club<>(persons);
        Workplace<Person> workplace = new Workplace<>(persons);

        club.rest();
        System.out.println();
        workplace.work();
    }
}
