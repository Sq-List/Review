package InnerClass;

public class Anonymous {
    public static void main(String[] args) {
        Item item = new Item(1);
        int a = 0;

        new Thread() {
            @Override
            public void run() {
                item.setA(2);
                System.out.println(a);
            }
        }.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(item.getA());
    }
}
