package oop.hw5.views;

import oop.hw5.models.Request;

import java.util.List;

public interface Viewing {

    Request createRequest();
    void printError();
    void printResponse(String str);
    void printHistory(List<String> history);
}
