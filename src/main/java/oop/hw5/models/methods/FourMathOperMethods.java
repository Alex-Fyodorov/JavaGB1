package oop.hw5.models.methods;

import oop.hw5.models.Request;

import java.util.List;

public interface FourMathOperMethods<T extends Number> {

    String sum(List<T> list, Request request);
    String diff(List<T> list, Request request);
    String mult(List<T> list, Request request);
    String div(List<T> list, Request request);

}
