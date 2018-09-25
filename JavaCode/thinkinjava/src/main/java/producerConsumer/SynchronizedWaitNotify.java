package producerConsumer;

import java.util.ArrayDeque;
import java.util.Queue;

public class SynchronizedWaitNotify {
    private static final int SIZE = 10;
    private static Queue<Integer> queue = new ArrayDeque<>(SIZE);

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
                synchronized (queue) {
                    try {
                        while (queue.size() == 0) {
                            System.out.println("Consumer " + id + " 等待生产者生产");
                            queue.wait();
                        }
                        Thread.sleep(1000);
                        queue.poll();
                        System.out.println("Consumer " + id + " 消费了一个， 队列中有" + queue.size() + "个");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        queue.notifyAll();
                    }
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
                synchronized (queue) {
                    try {
                        while (queue.size() == SIZE) {
                            System.out.println("Producer " + id + " 等待消费者消费");
                            queue.wait();
                        }
                        Thread.sleep(1000);
                        queue.offer(1);
                        System.out.println("Producer " + id + " 生产了一个， 队列中有" + queue.size() + "个");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        queue.notifyAll();
                    }
                }
            }
        }
    }
}
