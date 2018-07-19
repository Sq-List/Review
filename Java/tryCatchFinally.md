# try catch finally 执行顺序面试题总结
[原文链接](https://www.cnblogs.com/superFish2016/p/6687549.html)

---
```java
public static  int testBasic(){
    int i = 1; 
    try{
        i++;
        System.out.println("try block, i = "+i);
    }catch(Exception e){
        i ++;
        System.out.println("catch block i = "+i);
    }finally{
        i = 10;
        System.out.println("finally block i = "+i);
    }
    return i;
}
```
先执行try内代码段，没有异常的话进入finally，最后返回
结果：
try block, i = 2
finally block i = 10
main test i = 10

---
### 把return语句放入try catch
```java
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
}
```
结果：
try block, i = 2
finally block i = 10
main test i = 2

代码顺序执行从try到finally，由于finally是无论如何都会执行的，所以try里的语句并不会直接返回。在try语句的return块中，**return返回的引用变量并不是try语句外定义的引用变量i，而是系统重新定义了一个局部引用i’**，这个引用指向了引用i对应的值，也就是2，即使在finally语句中把引用i指向了值10，**因为return返回的引用已经不是i,而是i'，所以引用i的值和try语句中的返回值无关了。**

---
### 把i换成包装类型而不是基本类型
```java
public static  List<Object> testWrap(){
    List<Object> list = new ArrayList<>();
    try{
        list.add("try");
        System.out.println("try block");
        return list;
    }catch(Exception e){
        list.add("catch");
        System.out.println("catch block");
        return list;
    }finally{
        list.add("finally");
        System.out.println("finally block ");
    }
}
```
结果：
try block
finally block 
main test i = [try, finally]

基本类型在栈中存储，而对于 **非基本类型是存储在堆中的，返回的是堆中的地址**，因此内容被改变了。

---
### 在finally里加一个return
```java
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
        return i;
    }
}
```
结果：
try block, i = 2
finally block i = 10
main test i = 10

函数从finally语句块中返回。

---
### 在finally中有异常发生，对try、catch中异常的影响
```java
public static  int testBasic(){
    int i = 1; 
    try{
        i++;
        Integer.parseInt(null);
        System.out.println("try block, i = "+i);
        return i;
    }catch(Exception e){
        String.valueOf(null);
        System.out.println("catch block i = "+i);
        return i;
    }finally{
        i = 10;
        int m = i / 0;
        System.out.println("finally block i = "+i);
    }
}
```
结果：
Exception in thread "main" java.lang.ArithmeticException: / by zero
at tryandcatch.TryAndCatch.testBasic(TryAndCatch.java:25)
at tryandcatch.TryAndCatch.main(TryAndCatch.java:45)

这个提示表示的是finally里的异常信息，也就是说一旦finally里发生异常，try、catch里的异常信息即被消化掉了，也达不到异常信息处理的目的。


---
## 总结
1. finally总会执行
1. 如果try、catch中有return语句，finally中没有return语句，那么finally中 **修改除包装类和静态变量、全局变量**以外的数据都不会对try、catch中返回的变量有任何的影响（包装类型、静态变量、全局变量会改变）
1. 尽量不要在finally中使用return语句，如果使用的话，会忽略try、catch中的返回语句，也会忽略try、catch中的异常，屏蔽了错误的发生
1. finally中避免再次抛出异常，一旦finally中发生异常，代码执行将会抛出finally中的异常信息，try、catch中的异常将被忽略