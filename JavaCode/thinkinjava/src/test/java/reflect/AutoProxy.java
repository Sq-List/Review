package reflect;

import org.junit.Test;
import reflect.autoProxy.CalcDao;
import reflect.autoProxy.CalcDaoImpl;
import reflect.autoProxy.CalcDaoInvocationHandler;

import java.lang.reflect.Proxy;
import java.util.Arrays;

public class AutoProxy {

    @Test
    public void testAutoProxy() {
        CalcDao calcDao = new CalcDaoImpl();
        System.out.println(calcDao.getClass().getClassLoader());
        System.out.println(Arrays.asList(calcDao.getClass().getInterfaces()));

        CalcDao calcDaoProxy = (CalcDao) Proxy.newProxyInstance(
                calcDao.getClass().getClassLoader(),
                calcDao.getClass().getInterfaces(),
                new CalcDaoInvocationHandler(calcDao)
        );

        calcDaoProxy.add(10, 2);
        calcDaoProxy.sub(10, 2);
        calcDaoProxy.mul(10, 2);
        calcDaoProxy.div(10, 2);
    }
}
