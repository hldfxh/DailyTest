package debug;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDebug {

    public static void main(String[] args) throws Exception{
        CyclicBarrier barrier = new CyclicBarrier(3);
        for (int i = 0; i < 5 ; i++) {
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"进入");

                try {
                    barrier.await();
                } catch (Exception e) {
                }
                System.out.println(Thread.currentThread().getName()+"start");

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                }
                System.out.println(Thread.currentThread().getName()+"over");

            });
            thread.start();
        }
    }


}
