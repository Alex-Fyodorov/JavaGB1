package control.getter;

import control.model.Toy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Getter implements Getting {

    List<Toy> toyList = new ArrayList<>();
    List<Integer> idList = new ArrayList<>();
    Random random = new Random();

    @Override
    public void init(List<Toy> toys) {
        toyList.clear();
        idList.clear();
        for (Toy toy : toys) {
            toyList.add(toy);
            for (int i = 0; i < toy.getWeight(); i++) {
                idList.add(toy.getId());
            }
        }
    }

    @Override
    public Toy getToy() throws RuntimeException {
        if (idList.size() == 0) throw new RuntimeException("Список игрушек не заполнен");
        int id = idList.get(random.nextInt(idList.size()));
        return toyList.stream().filter(t -> t.getId() == id).findFirst()
                .orElseThrow(() -> new RuntimeException("Объект не найден."));
    }
}
