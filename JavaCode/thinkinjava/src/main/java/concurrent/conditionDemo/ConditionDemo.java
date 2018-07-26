package concurrent.conditionDemo;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    private static final int SIZE = 10;
    private static PriorityQueue<Integer> queue = new PriorityQueue<>(SIZE);
    private static Lock lock = new ReentrantLock();
    // 队列是否已满
    private static Condition notFull = lock.newCondition();
    // 队列是否已空
    private static Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        Producer producer = new Producer();

        consumer.start();
        producer.start();
    }

    static class Consumer extends Thread {
        private void consume() {
            for (; ; ) {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        System.out.println("队列为空，等待数据");
                        notEmpty.await();
                    }
                    Thread.sleep(1000);
                    queue.poll();
                    notFull.signal();
                    System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

        @Override
        public void run() {
            consume();
        }
    }

    static class Producer extends Thread {
        private void produce() {
            for (; ; ) {
                lock.lock();
                try {
                    while (queue.size() == SIZE) {
                        System.out.println("队列满，等待消费");
                        notFull.await();
                    }
                    Thread.sleep(500);
                    queue.offer(1);
                    notEmpty.signal();
                    System.out.println("向队列取中插入一个元素，队列剩余元素：" + queue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

        @Override
        public void run() {
            produce();
        }
    }
}
