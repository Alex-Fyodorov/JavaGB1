package oop.hw5.models.methods;

public class ComplToString implements ToStringMethod<Double> {

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
