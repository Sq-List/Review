package reflect.demo1;

public class A {

    public int i = 1;

    public static int b = 2;

    public A() {
        System.out.println("无参构造");
    }

    public A(Integer g) {
        System.out.println("有参构造" + g);
    }

    private A(String s) {
        System.out.println("有参构造" + s);
    }

    public void say() {
        System.out.println("say");
    }
}
