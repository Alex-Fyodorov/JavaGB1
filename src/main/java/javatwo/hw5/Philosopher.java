package javatwo.hw5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Philosopher extends Thread {
    private final String name;
    private final Lock lock;
    private final CyclicBarrier cyclicBarrier;

    public Philosopher(String name, Lock lock, CyclicBarrier cyclicBarrier) {
        this.name = name;
        this.lock = lock;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            boolean flag = true;
            boolean isLocked = false;
            while (flag) {
                try {
                    isLocked = lock.tryLock(100, TimeUnit.MILLISECONDS);
                    if (isLocked) {
                        eating(i);
                        lock.unlock();
                        flag = false;
                        isLocked = false;
                        thinking();
                        cyclicBarrier.await();
                    } else thinking();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (isLocked) lock.unlock();
                }
            }
        }
    }

    private void eating(int time) throws InterruptedException {
        Thread.sleep(500);
        switch (time) {
            case 1 -> System.out.printf("%s позавтракал.\n", name);
            case 2 -> System.out.printf("%s пообедал.\n", name);
            case 3 -> System.out.printf("%s поужинал.\n", name);
        }
    }

    private void thinking() throws InterruptedException {
        System.out.printf("    %s размышляет.\n", name);
        Thread.sleep(500);
    }
}
