import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test {
    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
//        String s2 = "123";
//        String s1 = "123";
//        System.out.println(s1 == s2);

//        String key = "123";
//        int h = key.hashCode();
//        System.out.println(h);
//        System.out.println(h >>> 16);
//        System.out.println((h) ^ (h >>> 16));
//        tableSizeFor(15);

//        System.out.println(exceptionReturn());
//        System.out.println(testBasic());
//        reflect();
//        testString();
        testConcurrentModificationException();
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        System.out.println(n);
        n |= n >>> 1;
        System.out.println(n);
        n |= n >>> 2;
        System.out.println(n);
        n |= n >>> 4;
        System.out.println(n);
        n |= n >>> 8;
        System.out.println(n);
        n |= n >>> 16;
        System.out.println(n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    static final Map exceptionReturn() {
        Map<String, String> stringMap = new HashMap<String, String>();
        stringMap.put("1", "1");
        try {
            throwException(1);
            return stringMap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            stringMap = null;
            return stringMap;
        }

//        return null;
    }

    static final void throwException(int i) throws Exception {
        if (i == 1) {
            throw new Exception();
        }
    }

    public static  int testBasic(){
        int i = 1;
        try{
            i++;
            System.out.println("try block, i = "+i);
            return i;
        }catch(Exception e){
            i ++;
            System.out.println("catch block i = "+i);
            return i;
        }finally{
            i = 10;
            System.out.println("finally block i = "+i);
        }
//        return i;
    }

    public static void reflect() {
        Class cls = int.class;
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }

    public static void testString() {
        String s1 = new String("aaa");
        String s2 = new String("aaa");
        System.out.println(s1 == s2);           // false
        String s3 = s1.intern();
        System.out.println(s1.intern() == s3);  // true
        String s4 = "aaa";
        System.out.println(s4 == s3);
    }

    public static void testConcurrentModificationException() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer integer = iterator.next();
            if(integer==2)
                list.remove(integer);
        }
    }
}
