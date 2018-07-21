package basics;

public interface TestInterface {

    void func1();

    default void func2() {
        System.out.println(123);
    }
}
