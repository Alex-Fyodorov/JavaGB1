package oop.hw5.models.methods;

import oop.hw5.models.Request;

public interface ToStringMethod<T extends Number> {

    String createAnswer(T x, T y);

    default String createStringFromEndNumbers(T x, T y, Request request) {
        StringBuilder sb = new StringBuilder();
        sb.append(request.getNumX());
        switch (request.getMethod()) {
            case (1) -> sb.append(" + ");
            case (2) -> sb.append(" - ");
            case (3) -> sb.append(" * ");
            case (4) -> sb.append(" / ");
        }
        sb.append(request.getNumY())
                .append(" = ")
                .append(createAnswer(x, y));
        return sb.toString();
    }
}
