package singleton;

/**
 *
 */
public class InnerClassSingleton {

    private static volatile InnerClassSingleton singleton;

    // 不加private怎么样
    private static class Singleton{
        // 还要加final,private public都行？
        private static final InnerClassSingleton innerClassSingleton = new InnerClassSingleton();
    }

    public static InnerClassSingleton getInstance(){
        return Singleton.innerClassSingleton;
    }

}
