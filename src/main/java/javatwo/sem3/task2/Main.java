package javatwo.sem3.task2;

public class Main {

    public static void main(String[] args) {
        Collect<Integer> collect = new Collect<>(4, 65 ,54 ,45, 65, 78, 12, 32, 98);
        System.out.println(collect);
        collect.add(13);
        System.out.println(collect);
        collect.remove(65);
        System.out.println(collect);

        Iter<Integer> iter = new Iter<>(collect.getArrT());

        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
