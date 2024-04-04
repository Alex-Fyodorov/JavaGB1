package algorithm.linkedList;

public class Answer {

    Node head;

    public void reverse() {
        Node one = this.head;
        Node two = one.next;
        Node three;
        while (two != null) {
            three = two.next;
            if (one == this.head) {
                one.next = null;
            }
            two.next = one;
            one = two;
            two = three;
        }
        this.head = one;
    }
}
