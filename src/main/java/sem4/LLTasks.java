package sem4;

import java.util.LinkedList;

public class LLTasks {
    public static void main(String[] args) {
        LinkedList<Object> ll = new LinkedList<>();
        ll.add(1);
        ll.add("One");
        ll.add(2);
        ll.add("Two");
        System.out.println(ll);
        System.out.println(revert(ll));
    }

    public static LinkedList<Object> revert(LinkedList<Object> ll) {
        LinkedList<Object> newList = new LinkedList<>();
        while (!ll.isEmpty()) {
            newList.add(ll.pollLast());
        }
        return newList;
    }
}
