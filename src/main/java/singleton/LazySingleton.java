package singleton;

/**
 * 懒汉模式，用的时候创建
 */
public class LazySingleton {

    private static volatile LazySingleton singleton = null;

    public static LazySingleton getSingleton(){
        if(singleton == null){
            synchronized (LazySingleton.class){
                if(singleton == null){
                    singleton = new LazySingleton();
                }
            }
        }
        return singleton;
    }
}
