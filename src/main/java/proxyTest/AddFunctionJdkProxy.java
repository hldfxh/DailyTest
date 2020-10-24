package proxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AddFunctionJdkProxy implements InvocationHandler {

    Object realOperator;

    public AddFunctionJdkProxy(Object realOperator) {
        this.realOperator = realOperator;
    }

    public Object getProxy() {
       return Proxy.newProxyInstance(realOperator.getClass().getClassLoader(),realOperator.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("do something here");
        method.invoke(realOperator,args);
        System.out.println("");
        return null;
    }
}
