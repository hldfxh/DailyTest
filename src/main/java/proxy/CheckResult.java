package proxy;

import java.lang.String;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

public class CheckResult {

    public static void main(String[] args) {
        //jdk
        //checkJDKproxy();

        //cglib
        //checkCGLIBproxy();
    }

    @Test
    public void checkJDKproxy() {
         System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        JdkProxy proxy = new JdkProxy(new Hello());
        IHello hello = (IHello) proxy.getProxy();

        hello.say("world");
    }

    @Test
    public void checkCGLIBproxy() {
         System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/admin/workSpace/FXH/javaLearn/generated/final");

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallback(new CglibProxy());

        Hello hello = (Hello) enhancer.create();
        hello.say("world");
    }

    private static void staticProxy() {
        HelloProxy hello = new HelloProxy();
        hello.say("world");
    }

    private static void testCost() {
        long count = 100L;

        long start = System.currentTimeMillis();
        JdkProxy proxy = new JdkProxy(new Hello());
        IHello hello = (IHello) proxy.getProxy();
        System.out.printf("jdk create cost "+ (System.currentTimeMillis()-start));

        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            hello.say("world");
        }
        System.out.printf("jdk cost "+ (System.currentTimeMillis()-start));

        start = System.currentTimeMillis();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallback(new CglibProxy());
        Hello hello2 = (Hello) enhancer.create();
        System.out.printf("cglib create cost "+ (System.currentTimeMillis()-start));

        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            hello2.say("world");
        }
        System.out.printf("cglib cost "+ (System.currentTimeMillis()-start));
    }
}
