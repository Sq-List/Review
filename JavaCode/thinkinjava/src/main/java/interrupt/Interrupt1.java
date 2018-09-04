package interrupt;

public class Interrupt1 implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new Interrupt1());

        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();


    }

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Yes,I am interruted,but I am still running");
            } else {
                System.out.println("not yet interrupted");
            }
        }
    }
}
