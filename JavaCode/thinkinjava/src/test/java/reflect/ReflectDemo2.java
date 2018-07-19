package reflect;

import org.junit.Test;
import reflect.demo2.Student;

import java.lang.reflect.Method;

public class ReflectDemo2 {

    /**
     * 获取当前类以及父类中的所有的公有方法
     * @throws Exception
     */
    @Test
    public void testGetMethods() throws Exception {
        Class cls = Class.forName("reflect.demo2.Student");
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }

    /**
     * 获取当前类中生命发的所有方法（包括私有方法）
     * @throws Exception
     */
    @Test
    public void testDeclareMethods() throws Exception {
        Class cls = Student.class;
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }

    /**
     * 获取当前类及父类中指定的公有方法
     * @throws Exception
     */
    @Test
    public void testMethod() throws Exception {
        Class cls = Student.class;
        Method method = cls.getMethod("method1");
        method.invoke(cls.newInstance());
        System.out.println(method);
    }

    /**
     * 获取当前类中声明的任意方法，包括私有方法。
     * 当要执行私有方法时，需要将方法的访问权限即method的accessible的属性设为true
     * @throws Exception
     */
    @Test
    public void testDeclaredMethod() throws Exception {
        Class cls = Student.class;
        Method method = cls.getDeclaredMethod("method4", int.class);
        // 访问权限即method的accessible的属性设为true
        method.setAccessible(true);
        method.invoke(cls.newInstance(), 1);
        System.out.println(method);
    }
}
