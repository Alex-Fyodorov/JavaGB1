package oop.hw5.models.methods;

public class IntToString implements ToStringMethod<Integer> {

    @Override
    public String createAnswer(Integer x, Integer y) {
        Double dbl = x.doubleValue() / y;
        int min = x;
        if (x > y) min = y;
        for (int i = 2; i <= min; i++) {
            if (x % i == 0 && y % i == 0) {
                x /= i;
                y /= i;
                i--;
            }
        }
        if (x >= y) {
            int num = x / y;
            x -= num * y;
            if (x == 0) {
                return String.format("%d", num);
            }
            return String.format("%d + %d / %d (%f)", num, x, y, dbl);
        }
        return String.format("%d / %d (%s)", x, y, dbl.toString());
    }
}
