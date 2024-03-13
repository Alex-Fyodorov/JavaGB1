package control.getter;

import control.model.Toy;

import java.util.List;

public interface Getting {
    void init(List<Toy> toys);
    Toy getToy() throws RuntimeException;
}
