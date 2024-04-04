package javatwo.hw3.task1;

public class Club<T extends Person> {
    private final MyIterator clubIterator;

    public Club(T[] visitors) {
        this.clubIterator = new MyIterator(visitors);
    }

    public void rest() {
        while (clubIterator.hasNext()) {
            clubIterator.next().haveRest();
        }
    }

    private class MyIterator {
        private final T[] val;
        private int index;

        public MyIterator(T[] val) {
            this.val = val;
            index = 0;
        }

        public boolean hasNext() {
            return index < val.length && val[index] != null;
        }

        public T next() {
            return val[index++];
        }
    }
}
