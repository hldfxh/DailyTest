import org.junit.Test;

public class InterruptTest {
//    /**
//     * 只有interrupt（） 和 print操作，不会停止
//     */
//    @Test
//    public void test(){
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int i = 0;
//                while (true) {
//                    System.out.println("is " + ++i);
//                   // if (Thread.interrupted()) {
//                    if (Thread.currentThread().isInterrupted()) {
//                        System.out.println("======");
//                    }
//                }
//            }
//        });
//        thread.start();
//        thread.interrupt();
//    }
//
//    @Test
//    public void testSleep(){
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int i = 0;
//                while (true) {
//                    System.out.println("is " + ++i);
//                    try {
//                        Thread.sleep(5000);
//                    } catch (Throwable e){
//                        System.out.println(e.getStackTrace());
//                    }
//                    // if (Thread.interrupted()) {
//                    if (Thread.currentThread().isInterrupted()) {
//                        System.out.println("======");
//                    }
//                }
//            }
//        });
//        thread.start();
//        thread.interrupt();
//    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                int i = 0;
                while (true) {
                    System.out.println("is " + ++i);
                    try {
                        wait(5000);
                    } catch (Throwable e){
                        e.printStackTrace();
                    }
                    // if (Thread.interrupted()) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("======");
                    }
                }
            }
        });
        thread.start();
        thread.interrupt();
    }
}
