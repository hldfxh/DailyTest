package proxyTest;

import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;
import proxy.CglibProxy;


public class ProxyTest {

    @Test
    public void jdkTest() {
        AddFunctionJdkProxy jdkProxy = new AddFunctionJdkProxy(new Sing());
        ISing proxy = (ISing) jdkProxy.getProxy();
        proxy.sing();
    }

    @Test
    public void cglibTest() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Sing.class);
        enhancer.setCallback(new CglibProxy());

        Sing sing = (Sing) enhancer.create();
        sing.sing();
    }
}
