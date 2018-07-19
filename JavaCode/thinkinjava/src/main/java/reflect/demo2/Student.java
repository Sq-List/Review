package reflect.demo2;

public class Student extends Person {

    private int stuno;

    public int getStuno() {
        return stuno;
    }

    public void setStuno(int stuno) {
        this.stuno = stuno;
    }

    public Student() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String method3() {
        return "Student类的公有无参方法method3()";
    }

    private void method4(int age) {
        System.out.println("Student类的私有带参方法method4(int age),实际参数为:" + age);
    }

}
