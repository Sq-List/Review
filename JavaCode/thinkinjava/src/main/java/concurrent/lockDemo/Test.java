package concurrent.lockDemo;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();    //注意这个地方

    public static void main(String[] args) {
        final Test test = new Test();

//        new Thread(() -> test.testLock(Thread.currentThread())).start();
//        new Thread(() -> test.testLock(Thread.currentThread())).start();

//        new Thread(() -> test.testTryLock(Thread.currentThread())).start();
//        new Thread(() -> test.testTryLock(Thread.currentThread())).start();

        Thread thread1 = new TestLockInterruptiblyThread(test);
        Thread thread2 = new TestLockInterruptiblyThread(test);
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }

    public void testLock(Thread thread) {
        lock.lock();
        try {
            System.out.println(thread.getName() + "得到了锁");
            for (int i = 0; i < 3; i++) {
                arrayList.add(i);
                System.out.println(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            System.out.println(thread.getName() + "释放了锁");
            lock.unlock();
        }
    }

    public void testTryLock(Thread thread) {
        if (lock.tryLock()) {
            try {
                System.out.println(thread.getName() + "得到了锁");
                for (int i = 0; i < 3; i++) {
                    arrayList.add(i);
                }
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                System.out.println(thread.getName() + "释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName() + "获取锁失败");
        }
    }

    public void testLockInterruptibly(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();   //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {
            System.out.println(thread.getName() + "得到了锁");
            long startTime = System.currentTimeMillis();
            for (; ; ) {
                if (System.currentTimeMillis() - startTime >= 60000)
                    break;
                //插入数据
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + "执行finally");
            lock.unlock();
            System.out.println(thread.getName() + "释放了锁");
        }
    }
}

class TestLockInterruptiblyThread extends Thread {
    private Test test = null;
    public TestLockInterruptiblyThread(Test test) {
        this.test = test;
    }
    @Override
    public void run() {

        try {
            test.testLockInterruptibly(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断");
        }
    }
}
