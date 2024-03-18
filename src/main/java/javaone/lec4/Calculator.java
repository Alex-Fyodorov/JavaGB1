package javaone.lec4;

public class Calculator {

    public int sum(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a * b;
    }

    public int division(int a, int b) {

        return a / b;
    }
}
