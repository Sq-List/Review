package producerConsumer;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockCondition {
    private static final int SIZE = 10;
    private static Queue<Integer> queue = new ArrayDeque<>(SIZE);
    private static ReentrantLock mainLock = new ReentrantLock();
    private static Condition consumerCondition = mainLock.newCondition();
    private static Condition producerCondition = mainLock.newCondition();

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Consumer(i).start();
        }

        for (int i = 0; i < 2; i++) {
            new Producer(i).start();
        }
    }

    static class Consumer extends Thread {
        private int id;

        public Consumer(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            for (;;) {
                mainLock.lock();
                try {
                    while (queue.size() == 0) {
                        System.out.println("Consumer " + id + " 等待生产者生产");
                        consumerCondition.await();
                    }
                    Thread.sleep(3000);
                    producerCondition.signal();
                    queue.poll();
                    System.out.println("Consumer " + id + " 消费了一个， 队列中有" + queue.size() + "个");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mainLock.unlock();
                }
            }
        }
    }

    static class Producer extends Thread {
        private int id;

        public Producer(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            for (;;) {
                mainLock.lock();
                try {
                    while (queue.size() == SIZE) {
                        System.out.println("Producer " + id + " 等待消费者消费");
                        producerCondition.await();
                    }
                    Thread.sleep(3000);
                    consumerCondition.signal();
                    queue.offer(1);
                    System.out.println("Producer " + id + " 生产了一个， 队列中有" + queue.size() + "个");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mainLock.unlock();
                }
            }
        }
    }
}
