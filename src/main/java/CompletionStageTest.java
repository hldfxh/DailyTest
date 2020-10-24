//import org.junit.Test;
//
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//
//public class CompletionStageTest {
//
//    /**
//     * TODO 很奇怪，sleep的话，"future run start" 和hello world都是等10s后才输出，无论start放在
//     * future前还是后
//     *
//     * @throws ExecutionException
//     * @throws InterruptedException
//     */
//    @Test
//    public void testThenApply() throws ExecutionException, InterruptedException {
//        //CompletedFuture 是openJdk里的
//        // CompletableFuture completableFuture = new CompletableFuture();
//        System.out.printf("future run start");
//        Thread.sleep(1000);
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");
//
//        CompletableFuture<String> objectCompletableFuture = future.thenApply(i -> {
//            // System.out.printf("future run start");
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return i + " world";
//        });
//        String join = objectCompletableFuture.get();
//        System.out.printf(join);
//    }
//
//    @Test
//    public void testCombine() {
//        String join = CompletableFuture
//                .supplyAsync(() -> getStringAndSleep(3000, "hello"))
//                .thenCombine(CompletableFuture.supplyAsync(() -> getStringAndSleep(2000, " world")), (a, b) -> a + b).join();
//        System.out.printf(join);
//    }
//
//    private String getStringAndSleep(int sleep, String value) {
//        try {
//            Thread.sleep(sleep);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return value;
//    }
//
//    @Test
//    public void testEither() {
//        String join = CompletableFuture.supplyAsync(() -> getStringAndSleep(3000, "hello")).applyToEither(CompletableFuture.supplyAsync(() -> getStringAndSleep(2000, " world")), a -> a).join();
//        System.out.printf(join);
//    }
//
//    /**
//     * thenCompose和thenCombine很相似，但更简洁
//     */
//    @Test
//    public void testCompose() {
//        String join = CompletableFuture.supplyAsync(() -> getStringAndSleep(3000, "hello"))
//                .thenCompose(s -> CompletableFuture.supplyAsync(() -> getStringAndSleep(2000, "ddf " + s))).join();
//        System.out.printf(join);
//    }
//
//    /**
//     * 会抛出异常到外面
//     */
//    @Test
//    public void whenCompleteTest() {
//        String result = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(4000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            if (1 == 1) {
//                throw new RuntimeException("测试一下异常情况");
//            }
//
//            return "hello ";
//        }).thenCombine(CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("return world...");  //会执行
//            return "world";
//        }), (s1, s2) -> {
//            String s = s1 + " " + s2;   //并不会执行
//            System.out.println("combine result :" + s); //并不会执行
//            return s;
//        }).whenComplete((s, t) -> {
//            System.out.println("current result is :" + s);
//            if (t != null) {
//                System.out.println("阶段执行过程中存在异常：");
//                t.printStackTrace();
//            }
//        }).join();
//
//        System.out.println("final result:" + result); //并不会执行
//    }
//
//    @Test
//    public void handleTest() {
//        String result = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            //出现异常
//            if (1 == 3) {
//                throw new RuntimeException("测试一下异常情况");
//            }
//            return "Tom";
//        }).handle((s, t) -> {
//            if (t != null) { //出现异常了
//                return "John";
//            }
//            return s; //这里也可以对正常结果进行转换
//        }).join();
//        System.out.println(result);
//    }
//
//    @Test
//    public void exceptionally() {
//        String result = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (1 == 1) {
//                throw new RuntimeException("测试一下异常情况");
//            }
//            return "s1";
//        }).exceptionally(e -> {
//            e.printStackTrace(); //e肯定不会null
//            return "hello world"; //补偿返回
//        }).join();
//        System.out.println(result); //打印hello world
//    }
//}
