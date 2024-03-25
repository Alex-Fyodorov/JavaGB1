package hw3;

public class Task {

    private String nameOfTask;

    public Task(String nameOfTask) {
        this.nameOfTask = nameOfTask;
    }

    public String getNameOfTask() {
        return nameOfTask;
    }

    public void setNameOfTask(String nameOfTask) {
        this.nameOfTask = nameOfTask;
    }

    @Override
    public String toString() {
        return "Task{" + nameOfTask + '}';
    }
}
