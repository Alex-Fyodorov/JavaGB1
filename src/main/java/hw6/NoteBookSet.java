package hw6;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class NoteBookSet {
    private Set<NoteBook> noteBooks;

    public NoteBookSet() {
        this.noteBooks = new HashSet<>();
    }

    public void add(NoteBook noteBook) {
        noteBooks.add(noteBook);
    }

    private Set<NoteBook> findByBrand(Set<NoteBook> nbSet, String brand) {
        return nbSet.stream().filter(o -> o.getBrand().equals(brand)).collect(Collectors.toSet());
    }

    private Set<NoteBook> findByRam(Set<NoteBook> nbSet, int ram) {
        return nbSet.stream().filter(o -> o.getRam() >= ram).collect(Collectors.toSet());
    }

    private Set<NoteBook> findByhardDiskCapacityBrand(Set<NoteBook> nbSet, int hdCapacity) {
        return nbSet.stream().filter(o -> o.getHardDiskCapacity() >= hdCapacity).collect(Collectors.toSet());
    }

    private Set<NoteBook> findByColor(Set<NoteBook> nbSet, String color) {
        return nbSet.stream().filter(o -> o.getColor().equals(color)).collect(Collectors.toSet());
    }

    private Set<NoteBook> findByOs(Set<NoteBook> nbSet, String os) {
        return nbSet.stream().filter(o -> o.getOs().equals(os)).collect(Collectors.toSet());
    }

    public Set<NoteBook> filterAll(Map<String, String> params) {
        Set<NoteBook> nb = new HashSet<>(noteBooks);
        for (Map.Entry<String, String> item : params.entrySet()) {
            switch (item.getKey()) {
                case "brand" -> nb = findByBrand(nb, item.getValue());
                case "os" -> nb = findByOs(nb, item.getValue());
                case "color" -> nb = findByColor(nb, item.getValue());
                case "ram" -> {
                    int ram = Integer.parseInt(item.getValue());
                    nb = findByRam(nb, ram);
                }
                case "hdCapacity" -> {
                    int hdCapacity = Integer.parseInt(item.getValue());
                    nb = findByhardDiskCapacityBrand(nb, hdCapacity);
                }
                default -> throw new IllegalStateException("Unexpected value: " + item.getKey());
            }
        }
        return nb;
    }

    //Метод нигде не используется. Нужен был для проверки
//    public void printAll() {
//        System.out.println(noteBooks);
//    }

    public void printSize() {
        System.out.printf("Общее количество ноутбуков: %d\n", noteBooks.size());
    }
}
