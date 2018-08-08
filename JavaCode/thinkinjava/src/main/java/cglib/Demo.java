package cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;


public class Demo {
    public void test() {
        System.out.println("Hello World!");
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Demo.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("method before...");
            Object object = methodProxy.invokeSuper(o, args);
            System.out.println("method after...");
            return object;
        });

        Demo demo = (Demo) enhancer.create();
        demo.test();

    }
}
