package reflect.autoProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class CalcDaoInvocationHandler implements InvocationHandler {
    private CalcDao calcDao;

    public CalcDaoInvocationHandler(CalcDao calcDao) {
        this.calcDao = calcDao;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法[" + method.getName() + "]开始执行, 参数为" + Arrays.asList(args) + "...");
        long start = System.currentTimeMillis();
        Object result = method.invoke(calcDao, args);
        long end = System.currentTimeMillis();
        System.out.println("方法[" + method.getName() + "]执行完成");
        System.out.println("运算结果为:" + result + ", 用时" + (end - start) + "毫秒!");
        return result;
    }
}
