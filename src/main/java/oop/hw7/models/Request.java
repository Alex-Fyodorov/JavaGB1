package oop.hw7.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {
    private int calculator;
    private int method;
    private String numX;
    private String numY;
}
