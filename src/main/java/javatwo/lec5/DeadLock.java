package javatwo.lec5;

public class DeadLock {

    public static void main(String[] args) {
        Object lockA = new Object();
        Object lockB = new Object();

        new Thread(() -> {
            synchronized (lockA) {
                System.out.println("a");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lockB) {
                    System.out.println("b");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lockB) {
                System.out.println("b");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lockA) {
                    System.out.println("a");
                }
            }
        }).start();
    }
}
