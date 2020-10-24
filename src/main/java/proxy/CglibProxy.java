package proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    @Override public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib before do");

        methodProxy.invokeSuper(o,objects);
        //method.invoke(o,objects);//陷入循环调用
        System.out.println("cglib after do");

        return null;
    }
}
