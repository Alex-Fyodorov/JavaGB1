package javatwo.lec5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Task3 {

    static int count = 1;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                int num = count++;
                try {
                    System.out.println(num + " ready");
                    cyclicBarrier.await();
                    int time = (int) (200000 / (Math.random() * 10 + 20));
                    Thread.sleep(time);
                    System.out.println(num + " on finish");
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        try {
            cyclicBarrier.await();
            System.out.println("start");
            Thread.sleep(1000);
            cyclicBarrier.await();
            System.out.println("stop");
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
