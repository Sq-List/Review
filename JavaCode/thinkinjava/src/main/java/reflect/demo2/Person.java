package reflect.demo2;

public class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void method1() {
        System.out.println("person类: method1()");
    }

    private int method2() {
        return 0;
    }

}
