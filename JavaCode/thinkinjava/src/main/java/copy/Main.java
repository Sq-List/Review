package copy;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        A a = new A();
        a.setA(500);

        A aa = (A) a.clone();
        System.out.println(a.getA() == aa.getA());
        aa.setA(600);
        System.out.println(a.getA());
        System.out.println(aa.getA());
        System.out.println(a.getA() == aa.getA());
    }
}
