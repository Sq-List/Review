package reflect.demo1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        // 返回A的构造方法
        Constructor c = A.class.getConstructor();
        System.out.println(c);

        // 返回A的所有public声明的构造方法
        Constructor[] cons = A.class.getConstructors();
        System.out.println(cons);

        // 返回A的所有构造方法，包括private
        Constructor[] cons2 = A.class.getDeclaredConstructors();
        System.out.println(cons2);

        // 返回A的第一个public方法
        Method m = A.class.getMethod("say");
        // 执行
        m.invoke(A.class.newInstance(), null);

        // 返回A的所有public方法
        Method[] ms = A.class.getMethods();
        System.out.println(ms);

        // 返回A的所有方法，包括private
        Method[] allMs = A.class.getDeclaredMethods();
        System.out.println(allMs);

        // 返回A的public字段
        Field field = A.class.getField("i");
        System.out.println(field.get(A.class.newInstance()));

        // 返回A的static字段
        Field field1 = A.class.getField("b");
        System.out.println(field1.get(null));
    }
}
