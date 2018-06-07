package InnerClass;

/**
 * 静态内部类
 */
public class StaticOuterClass {
    private Integer count = 1;

    static class InnerClass {
        private Integer count = 2;
    }

    public static void main(String[] args) {
        StaticOuterClass.InnerClass innerClass = new StaticOuterClass.InnerClass();
    }
}
