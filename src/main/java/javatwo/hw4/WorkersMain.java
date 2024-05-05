package javatwo.hw4;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class WorkersMain {

    public static void main(String[] args) {
        WorkersDirectory workersDirectory = new WorkersDirectory();
        workersDirectory.add(new Worker(1, "Tom", List.of("111111", "222222"),
                LocalDate.of(2012, Month.APRIL, 15)));
        workersDirectory.add(new Worker(2, "Judy", List.of("33333", "44444"),
                LocalDate.of(2010, Month.MAY, 1)));
        workersDirectory.add(new Worker(3, "James", List.of("5555555"),
                LocalDate.of(2014, Month.OCTOBER, 21)));
        workersDirectory.add(new Worker(4, "John", List.of("666666", "7777"),
                LocalDate.of(2013, Month.DECEMBER, 8)));
        workersDirectory.add(new Worker(5, "Pamella", List.of("8888", "9999999"),
                LocalDate.of(2013, Month.APRIL, 28)));
        workersDirectory.add(new Worker(6, "Alex", List.of("1010101", "202020"),
                LocalDate.of(2013, Month.APRIL, 13)));
        workersDirectory.add(new Worker(7, "William", List.of("3030303", "4040404"),
                LocalDate.of(2012, Month.JULY, 17)));

        try {
            System.out.println(workersDirectory.findById(5));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(workersDirectory.findByName("John"));
        try {
            System.out.println(workersDirectory.findByPhone("30303033"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(workersDirectory.findByWorkExperience(10));
    }
}
