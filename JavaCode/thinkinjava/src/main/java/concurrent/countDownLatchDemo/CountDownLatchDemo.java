package concurrent.countDownLatchDemo;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        Runnable runnable = () -> {
            try {
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                Thread.sleep(1000);
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
                System.out.println("123");
            }
        };

        new Thread(runnable).start();

        new Thread(runnable).start();

        try {
            System.out.println("等待两个子线程执行完毕");
            countDownLatch.await();
            System.out.println("两个子线程执行完毕");
            System.out.println("执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
