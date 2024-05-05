package javathree.hw2;

public class AnnotationApp {

    public static void main(String[] args) {
        MyDate myDate = new MyDate();
        RandomDateProcessor.processRandomDate(myDate);

        System.out.println(myDate.getDate());
        System.out.println(myDate.getInstant());
        System.out.println(myDate.getLocalDate());
        System.out.println(myDate.getLocalDateTime());
    }
}
