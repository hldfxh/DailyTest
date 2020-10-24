package proxy;

import java.lang.String;

public class Hello implements IHello {

    @Override public final void say(String name) {
        System.out.println("hello " + name);
    }

}



