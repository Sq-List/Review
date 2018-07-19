/**
 * 单例模式
 */

// 饿汉模式
public class Singteton1 {
    private static INSTANCE = new Singteton1();

    private Singteton1() {

    }

    public static getSingteton1() {
        return INSTANCE;
    }
}

// 懒汉模式
public class Singteton2 {
    private static INSTANCE;

    private Singteton2() {

    }

    public static Singteton2 getSingteton2() {
        if (INSTANCE == null) {
            INSTANCE = new Singteton2();
        }
        return INSTANCE;
    }
}

public class Singteton3 {
    private volatile static INSTANCE;

    private Singteton3() {

    }

    public static Singteton3 getSingteton3() {
        if (INSTANCE == null) {
            synchronized(Singteton3.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singteton3();
                }
            }
        }
        return INSTANCE;
    }
}