package oop.hw7.models.toString;

public class IntToString implements ToStringMethod<Integer> {

    /**
     * Метод переводит ответ, полученный от калькулятора в виде дроби в строковую форму.
     * @param x Числитель.
     * @param y Знаменатель.
     * @return Ответ в виде строки. Если число не целое выдаёт ответ в двух представлениях:
     * в виде обычной дроби и в виде десятичной.
     */
    @Override
    public String createAnswer(Integer x, Integer y) {
        Double dbl = x.doubleValue() / y;
        int index = x / Math.abs(x);
        x = Math.abs(x);
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
                return String.format("%d", num * index);
            }
            return String.format("%d %s %d/%d (%f)", num * index, index > 0 ? "+" : "-", x, y, dbl);
        }
        return String.format("%d/%d (%s)", x * index, y, dbl.toString());
    }
}
