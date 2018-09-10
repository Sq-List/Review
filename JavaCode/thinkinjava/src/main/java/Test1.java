class Fruit {}
class Apple extends Fruit {}
class Orange extends Fruit {}
class Jonathan extends Apple {}

public class Test1 {
    public static void main(String[] args) {
        Fruit[] fruits = new Apple[10];

        fruits[0] = new Jonathan();

        fruits[0] = new Apple();

        fruits[0] = new Orange();   // exception

        fruits[0] = new Fruit();    // exception
    }
}
