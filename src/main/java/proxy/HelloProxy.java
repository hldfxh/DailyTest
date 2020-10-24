package proxy;

import java.lang.String;

public class HelloProxy implements IHello {
    private Hello hello;
    public HelloProxy(){
        hello = new Hello();
    }

    private void before() {
        System.out.println("do before");
    }

    private void after(){
        System.out.println("do after");
    }

    @Override
    public void say(String name){
        before();
        hello.say(name);
        after();
    }
}




