package javatwo.hw4;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorkersDirectory {

    private List<Worker> workers;

    public WorkersDirectory() {
        workers = new ArrayList<>();
    }

    public WorkersDirectory(List<Worker> workers) {
        this.workers = workers;
    }

    public void add(Worker worker) {
        workers.add(worker);
    }

    public Worker findById(int id) throws Exception {
        return workers.stream().filter(w -> w.getId() == id)
                .findFirst().orElseThrow(() -> new Exception(
                        String.format("Worker with id %d not found.", id)));
    }

    public List<Worker> findByName(String name) {
        return workers.stream().filter(w -> w.getName().equals(name)).collect(Collectors.toList());
    }

    public Worker findByPhone(String phone) throws Exception {
        return workers.stream().filter(w -> w.getPhones().contains(phone))
                .findFirst().orElseThrow(() -> new Exception(
                        String.format("Worker with phone %s not found.", phone)));
    }

    public List<Worker> findByWorkExperience(int years) {
        return workers.stream()
                .filter(w -> ChronoUnit.YEARS.between(w.getStartToWork(), LocalDate.now()) == years)
                .collect(Collectors.toList());
    }
}
