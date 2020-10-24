package concurrent;

public class DeadLockTest {
    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (a){
                    try {
                        System.out.println("in a");
                        Thread.sleep(1000);
                        synchronized (b){
                            System.out.println("in b");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (b){
                    System.out.println("2 in b");
                    synchronized (a){
                        System.out.println("2 in a");
                    }
                }
            }
        });

        thread1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}
