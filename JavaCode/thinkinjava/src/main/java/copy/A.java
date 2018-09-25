package copy;

public class A implements Cloneable {
    private Integer a;

    public Integer getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
