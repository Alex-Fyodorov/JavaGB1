package oop.sem4;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LRUCache<T> {
    private List<T> list;
    private final int size;

    public void setList(List<T> list) {
        for (T t : list) {
            this.list.add(t);
        }
    }

    public LRUCache(int size) {
        this.size = size;
        list = new ArrayList<>();
    }

    public void addElement(T element) {
        list.add(element);
        if (list.size() > size) list.remove(0);
    }

    public T getElement(int index) {
        return list.get(index);
    }

    public List<T> getAllElement() {
        return list;
    }
}
