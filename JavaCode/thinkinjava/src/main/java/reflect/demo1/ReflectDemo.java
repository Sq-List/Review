package reflect.demo1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        // 返回A的构造方法
        Constructor c = A.class.getConstructor();
        System.out.println(c);

        Constructor cs = A.class.getConstructor(int.class);
        System.out.println(cs);

        // 返回A的所有public声明的构造方法
        Constructor[] cons = A.class.getConstructors();
        System.out.println(Arrays.asList(cons));

        // 返回A声明的所有构造方法，包括private
        Constructor[] cons2 = A.class.getDeclaredConstructors();
        System.out.println(Arrays.asList(cons2));

        // 返回A的第一个public方法
        Method m = A.class.getMethod("say");
        // 执行
        m.invoke(A.class.newInstance(), null);

        // 返回A的所有public方法（包括父类）
        Method[] ms = A.class.getMethods();
        System.out.println(Arrays.asList(ms));

        // 返回A声明的所有方法，包括private
        Method[] allMs = A.class.getDeclaredMethods();
        System.out.println(Arrays.asList(allMs));

        // 返回A的public字段
        Field field = A.class.getField("i");
        A a = A.class.newInstance();
        field.set(a, 100);
        System.out.println(field.get(a));

        // 返回A的static字段
        Field field1 = A.class.getField("b");
        System.out.println(field1.get(null));

        // 返回A所有public字段
        Field[] fields = A.class.getFields();
        System.out.println(Arrays.asList(fields));

        // 返回A声明的所有字段
        Field[] fields1 = A.class.getDeclaredFields();
        System.out.println(Arrays.asList(fields1));

        Method method1 = B.class.getMethod("hello");
        Method method2 = B.class.getMethod("hello", String.class);
    }
}
