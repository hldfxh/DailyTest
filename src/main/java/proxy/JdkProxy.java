package proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {

    Object target;

    public JdkProxy(Object target) {
        this.target = target;
    }

    Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),this);
    }

    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("jdk before do");
        method.invoke(target,args);
        System.out.println("jdk after do");
        return null;
    }
}
