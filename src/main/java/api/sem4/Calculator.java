package api.sem4;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator {
    private Deque<Integer> dq;

    public Calculator() {
        this.dq = new ArrayDeque<>();
    }

    public static void main(String[] args) {
        Calculator hw = new Calculator();
        System.out.println(hw.calculate('*', 3, 2));
        System.out.println(hw.calculate('-', 7, 4));
        System.out.println(hw.calculate('<', 0, 0));
    }

    public int calculate(char op, int a, int b) {
        switch (op) {
            case '+':
                dq.add(a + b);
                return a + b;
            case '-':
                dq.add(a - b);
                return a - b;
            case '*':
                dq.add(a * b);
                return a * b;
            case '/':
                dq.add(a / b);
                return a / b;
            case '<':
                dq.pollLast();
                return dq.peekLast();
            default:
                System.out.printf("Некорректный оператор: %c", op);
        }
        return 0;
    }
}
