package javatwo.hw3.task1;

public class Workplace<T extends Person> {
    private final MyIterator workplaceIterator;

    public Workplace(T[] workers) {
        this.workplaceIterator = new MyIterator(workers);
    }

    public void work() {
        while (workplaceIterator.hasNext()) {
            workplaceIterator.next().doWork();
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
