package InnerClass.PartInnerClass;

/**
 * 方法内的局部内部类
 * PDestination类是destination()方法的一部分，
 * destination之内不能访问PDestination
 */
public class Parcel5 {
    public Destination destination(String s) {
        class PDestination implements Destination {
            private String label;

            private PDestination(String whereTo) {
                label = whereTo;
            }

            public String readLabel() {
                return label;
            }
        }
        return new PDestination(s);
    }

    public static void main(String[] args) {
        Parcel5 parcel5 = new Parcel5();
        Destination d = parcel5.destination("Heaven");
    }
}
