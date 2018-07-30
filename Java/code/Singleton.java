/**
 * 单例模式
 */

// 饿汉模式
public class Singleton1 {
    private static final INSTANCE = new Singleton1();

    private Singleton1() {

    }

    public static getSingleton1() {
        return INSTANCE;
    }
}

// 懒汉模式
public class Singleton2 {
    private static INSTANCE;

    private Singleton2() {

    }

    public static Singleton2 getSingleton2() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton2();
        }
        return INSTANCE;
    }
}

public class Singleton3 {
    private volatile static INSTANCE;

    private Singleton3() {

    }

    public static Singleton3 getSingleton3() {
        if (INSTANCE == null) {
            synchronized(Singleton3.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton3();
                }
            }
        }
        return INSTANCE;
    }
}

public class Singleton4 {
    private static class SingletonHolder {
        private static final Singleton4 INSTANCE = new Singleton4();
    }

    private Singleton4() {

    }

    public static Singleton4 getSingleton4() {
        return SingletonHolder.INSTANCE;
    }
}