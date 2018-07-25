package concurrent.cyclicBarrierDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        int N = 4;
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(N);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, () -> {
            System.out.println("当前线程" + Thread.currentThread().getName());
        });

        for (int i = 0; i < N - 1; i++) {
            if(i<N-1)
                new Writer(cyclicBarrier).start();
            else {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Writer(cyclicBarrier).start();
            }
        }
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
//                cyclicBarrier.await(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch(BrokenBarrierException e) {
                e.printStackTrace();
//            } catch (TimeoutException e) {
//                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
}
