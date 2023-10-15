package oop.hw5.converters;

import oop.hw5.models.Request;

import java.util.List;

public interface Convertering<T extends Number> {

    List<T> requestToNumbers(Request request);
}
