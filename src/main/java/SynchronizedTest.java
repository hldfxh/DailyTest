//import org.junit.Test;
//import org.openjdk.jol.info.ClassLayout;
//import sun.misc.Unsafe;
//
//import java.java.lang.reflect.Field;
//
//public class SynchronizedTest {
//
//    @Test
//    public void test1() throws Exception {
//        Object o = new Object();
//        printLockHeader(o);
//    }
//
//    /**
//     * 无锁状态，第一次枷锁变 轻量级锁
//     * @throws Exception
//     */
//    @Test
//    public void test2() throws Exception {
//        Object o = new Object();
//        printLockHeader(o);
//        synchronized (o) {
//            System.out.println("对象synchronized之后：");
//            printLockHeader(o);
//        }
//    }
//
//    /**
//     * 线程运行一段时间后：创建对象，直接是偏向锁状态，但此时偏向的 thread id = 0
//     * synchronized 后 仍为偏向锁，thread id = 实际线程ID
//     * 重入后 仍为偏向锁 thread ID 不变
//     *
//     * 注：若sleep 1000，则时间不足，无锁->轻量级锁->轻量级锁
//     * @throws Exception
//     */
//    @Test
//    public void test3() throws Exception {
//        Thread.sleep(5000);
//        Object o = new Object();
//        printLockHeader(o);
//        //Thread.sleep(5000);
//        synchronized (o) {
//            System.out.println("对象synchronized之后：");
//            printLockHeader(o);
//            System.out.println("偏向锁重入后：");
//            synchronized (o) {
//                printLockHeader(o);
//            }
//        }
//    }
//
//    /**
//     * sleep 10000误放到synchronized里，主线程执行完就停止了，不管异步线程了
//     * sleep 10000注释掉了，也还是偏向锁->轻量级锁
//     *
//     * 多个线程使用、不竞争
//     * 偏向锁 threadid =0 -> 偏向锁 thread id = xx -> 轻量级锁
//     * @throws Exception
//     */
//    @Test
//    public void test4() throws Exception{
//        Thread.sleep(5000);
//        Object o = new Object();
//        printLockHeader(o);
//        new Thread(()->{
//            synchronized (o) {
//                System.out.println("thread 1 进来了："+Thread.currentThread().getId());
//                try {
//                    printLockHeader(o);
//                } catch (Exception e) {
//                    System.err.println(e);
//                }
//            }
////            try {
////                Thread.sleep(10000);
////            } catch (InterruptedException e) {
////                System.err.println(e);
////            }
//        }).start();
//        Thread.sleep(1000);
//        System.out.println("锁释放了");
//
//        new Thread(()->{
//            synchronized (o) {
//                System.out.println("thread 2 进来了："+Thread.currentThread().getId());
//                try {
//                    printLockHeader(o);
//                } catch (Exception e) {
//                    System.err.println(e);
//                }
//            }
//        }).start();
//
//    }
//
//    /**
//     * 重量级锁
//     * @throws Exception
//     */
//    @Test
//    public void test5() throws Exception{
//        Thread.sleep(5*1000);
//        Object a = new Object();
//        printLockHeader(a);
//        new Thread(
//                ()->{
//                    synchronized (a){
//                        System.out.println("线程1对a加锁后");
//                        try {
//                            printLockHeader(a);
//                            Thread.sleep(1000);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//        ).start();
//        Thread.sleep(500);
//        new Thread(
//                ()->{
//                    synchronized (a){
//                        System.out.println("线程2对a加锁后");
//                        try {
//                            printLockHeader(a);
//                            Thread.sleep(1000);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//        ).start();
//        Thread.sleep(2000);
//    }
//
//    private static Unsafe getUnsafe() throws Exception {
//        Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
//        Field field = unsafeClass.getDeclaredField("theUnsafe");
//        field.setAccessible(true);
//        return  (Unsafe) field.get(null);
//    }
//    private static void printLockHeader(Object obj) throws Exception {
//        String s = ClassLayout.parseInstance(obj).toPrintable();
//        System.out.printf(s);
//
//        Unsafe us =  getUnsafe();
//        StringBuilder sb = new StringBuilder();
//        int status = us.getByte(obj, 0L) & 0B11;
//        // 0 轻量级 1 无锁或偏向 2 重量级 3 GC标记
//        switch (status){
//            case 0:
//                // ptr_to_lock_record:62|lock:2
//                long ptrToLockRecord =
//                        (byteMod(us.getByte(obj, 0L))>>2) +
//                                (byteMod(us.getByte(obj, 1L))<<6) +
//                                (byteMod(us.getByte(obj, 2L))<<14) +
//                                (byteMod(us.getByte(obj, 3L))<<22) +
//                                (byteMod(us.getByte(obj, 4L))<<30) +
//                                (byteMod(us.getByte(obj, 5L))<<38) +
//                                (byteMod(us.getByte(obj, 6L))<<46) +
//                                (byteMod(us.getByte(obj, 7L))<<54);
//                sb.append("锁状态：轻量级锁，LockRecord地址：")
//                        .append(Long.toHexString(ptrToLockRecord))
//                ;
//                break;
//            case 1:
//                boolean biased = (us.getByte(obj, 0L)&4) == 4;
//                if(!biased){
//                    // unused:25 | identity_hashcode:31 | unused:1 | age:4 | biased_lock:1 | lock:2
//                    int hashCode = (int)(byteMod(us.getByte(obj, 1L))
//                            + (byteMod(us.getByte(obj, 2L))<<8)
//                            + (byteMod(us.getByte(obj, 3L))<<16)
//                            + ((byteMod(us.getByte(obj, 4L))&Integer.MAX_VALUE) <<24))
//                            ;
//                    int age = (us.getByte(obj,0L)>>3)&0B1111;
//                    sb.append("锁状态：无锁，hashCode：")
//                            .append(hashCode)
//                            .append(",age: ")
//                            .append(age);
//                }else{
//                    //thread:54|epoch:2|unused:1| age:4 | biased_lock:1 | lock:2
//                    long thread = (byteMod(us.getByte(obj, 1L))>>2) +
//                            (byteMod(us.getByte(obj, 2L))<<6) +
//                            (byteMod(us.getByte(obj, 3L))<<14) +
//                            (byteMod(us.getByte(obj, 4L))<<22) +
//                            (byteMod(us.getByte(obj, 5L))<<30) +
//                            (byteMod(us.getByte(obj, 6L))<<38) +
//                            (byteMod(us.getByte(obj, 7L))<<46);
//                    ;
//                    int epoch = us.getByte(obj, 1L) & 0B11;
//                    int age = (us.getByte(obj,0L)>>3)&0B1111;
//                    sb.append("锁状态：偏向锁，thread：")
//                            .append(thread)
//                            .append(",epoch: ")
//                            .append(epoch)
//                            .append(",age: ")
//                            .append(age);
//                }
//                break;
//            case 2:
//                // ptr_to_heavyweight_monitor:62| lock:2
//                long ptrToMonitor =
//                        (byteMod(us.getByte(obj, 0L))>>2) +
//                                (byteMod(us.getByte(obj, 1L))<<6) +
//                                (byteMod(us.getByte(obj, 2L))<<14) +
//                                (byteMod(us.getByte(obj, 3L))<<22) +
//                                (byteMod(us.getByte(obj, 4L))<<30) +
//                                (byteMod(us.getByte(obj, 5L))<<38) +
//                                (byteMod(us.getByte(obj, 6L))<<46) +
//                                (byteMod(us.getByte(obj, 7L))<<54);
//                sb.append("锁状态：重量级锁，Monitor地址：")
//                        .append(Long.toHexString(ptrToMonitor))
//                ;
//                break;
//            case 3:
//                sb.append("锁状态：GC标记");
//                break;
//            default:
//                break;
//        }
//        if(obj instanceof Object[]){
//            int arrLen = us.getInt(obj, 3L);
//            sb.append("对象为数组类型，数组长度:")
//                    .append(arrLen);
//        }
//        sb.append("\n").append("---------------").append("\n");
//        System.out.println(sb.toString());
//    }
//
//    private static long byteMod(byte b){
//        if(b>=0){
//            return b;
//        }
//        return b + 256;
//    }
//}
