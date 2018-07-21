package concurrent.synchronizedDemo;

public class Main {
    public static void main(String[] args)  {
        final InsertData insertData = new InsertData();

        new Thread(() -> insertData.insert(Thread.currentThread())).start();

        new Thread(() -> insertData.insert(Thread.currentThread())).start();
    }
}
