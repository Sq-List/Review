package wangyi.autumn2018;

public class Main4 {
    public static void main(String[] args) {
        Point point = new Point(-1, 1);
        System.out.println(point);
        point.rotate();
        System.out.println(point);
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void rotate() {
        double a = Math.toRadians(90);
        System.out.println((x * Math.cos(a) - y * Math.sin(a)));
        System.out.println((x * Math.sin(a) + y * Math.cos(a)));
        int nowX = (int) (x * Math.cos(a) - y * Math.sin(a));
        int nowY = (int) (x * Math.sin(a) + y * Math.cos(a));

        x = nowX;
        y = nowY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
