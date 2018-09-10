package Alibaba.autumn2018;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String nowString = in.nextLine();
        String[] nowStringArr = nowString.split(",");
        Point now = new Point(Long.parseLong(nowStringArr[0]), Long.parseLong(nowStringArr[1]));

        String pointsString = in.nextLine();
        String[] pointsStringArr = pointsString.split(",");
        Point[] points = new Point[pointsStringArr.length / 2];

        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(Long.parseLong(pointsStringArr[2 * i]), Long.parseLong(pointsStringArr[2 * i + 1]));
        }

        long ans = Long.MAX_VALUE;
        if (containsPoint(now, points)) {
            System.out.println("yes,0");
        } else {
            for (int i = 0; i < points.length; i++) {
                long tmp = Math.round(pointToLine(points[i], points[(i + 1) % points.length], now));
                ans = ans < tmp ? ans : tmp;
            }

            if (ans == 0) {
                System.out.println("yes,0");
            } else {
                System.out.println("no," + ans);
            }
        }

    }

    public static boolean containsPoint(Point point, Point[] points) {
        int verticesCount = points.length;
        int nCross = 0;
        for (int i = 0; i < verticesCount; ++i) {
            Point p1 = points[i];
            Point p2 = points[(i + 1) % verticesCount];

            // 求解 y=p.y 与 p1 p2 的交点
            if (p1.getY() == p2.getY()) {   // p1p2 与 y=p0.y平行
                continue;
            }
            if (point.getY() < Math.min(p1.getY(), p2.getY())) { // 交点在p1p2延长线上
                continue;
            }
            if (point.getY() >= Math.max(p1.getY(), p2.getY())) { // 交点在p1p2延长线上
                continue;
            }
            // 求交点的 X 坐标
            float x = (point.getY() - p1.getY()) * (p2.getX() - p1.getX()) / (p2.getY() - p1.getY()) + p1.getX();
            if (x > point.getX()) { // 只统计单边交点
                nCross++;
            }
        }
        // 单边交点为偶数，点在多边形之外
        return (nCross % 2 == 1);
    }

    /**
     * 求 p0 到 p1、p2 所组成直线的距离
     * @param p1
     * @param p2
     * @param p0
     * @return
     */
    public static double pointToLine(Point p1, Point p2, Point p0) {
        double space = 0;
        double a, b, c;
        a = lineSpace(p1, p2);// 线段的长度
        b = lineSpace(p1, p0);// p1到点的距离
        c = lineSpace(p2, p0);// p2到点的距离
        if (c <= 0.000001 || b <= 0.000001) {
            space = 0;
            return space;
        }
        if (a <= 0.000001) {
            space = b;
            return space;
        }
        if (c * c >= a * a + b * b) {
            space = b;
            return space;
        }
        if (b * b >= a * a + c * c) {
            space = c;
            return space;
        }
        double p = (a + b + c) / 2;// 半周长
        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));// 海伦公式求面积
        space = 2 * s / a;// 返回点到线的距离（利用三角形面积公式求高）
        return space;
    }

    // 计算两点之间的距离
    public static double lineSpace(Point p1, Point p2) {
        double lineLength = 0;
        lineLength = Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) + (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()));
        return lineLength;
    }
}

class Point {
    private long x;
    private long y;

    public Point(long x, long y) {
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

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }
}
