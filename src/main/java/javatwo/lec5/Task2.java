package javatwo.lec5;

public class Task2 {

    static boolean flag = true;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            long start = System.currentTimeMillis();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                flag = !flag;
                System.out.println((System.currentTimeMillis() - start) + "-1");
                start = System.currentTimeMillis();
            }
        });

        Thread thread2 = new Thread(() -> {
            int count = 100;
            while (count >= 0) {
                if (flag) {
                    System.out.println(count--);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            thread1.interrupt();
        });

        thread1.start();
        thread2.start();
    }
}
