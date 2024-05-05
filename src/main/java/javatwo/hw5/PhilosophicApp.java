package javatwo.hw5;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PhilosophicApp {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

        new Philosopher("Аристотель", lock, cyclicBarrier).start();
        new Philosopher("Сократ", lock, cyclicBarrier).start();
        new Philosopher("Платон", lock, cyclicBarrier).start();
        new Philosopher("Демокрит", lock, cyclicBarrier).start();
        new Philosopher("Диоген", lock, cyclicBarrier).start();
    }
}
