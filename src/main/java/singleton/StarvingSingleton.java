package singleton;

/**
 * 饿汉模式，一开始就创建
 */
public class StarvingSingleton {

    private static StarvingSingleton singleton = new StarvingSingleton();

    public static StarvingSingleton getSingleton(){
        return singleton;
    }
}
