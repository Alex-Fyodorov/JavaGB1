package oop.hw7.models.toString;

public class ComplToString implements ToStringMethod<Double> {

    /**
     * Метод переводит ответ, полученный от калькулятора в строковую форму.
     * @param x Первое число.
     * @param y Первое число.
     * @return Ответ в виде строки.
     */
    @Override
    public String createAnswer(Double x, Double y) {
        StringBuilder sb = new StringBuilder();
        sb.append(x);
        if (y < 0) {
            sb.append(" - ")
                    .append(Math.abs(y));
        } else {
            sb.append(" + ").append(y);
        }
        sb.append("*i");
        return sb.toString();
    }
}
