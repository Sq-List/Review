package Alibaba;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n;
        Scanner in = new Scanner(System.in);
        while ((n = in.nextInt()) != 0) {
            List<Point> list = new ArrayList();
            in.nextLine();
            for (int i = 0; i < n; i++) {
                String line = in.nextLine();
                String[] arr = line.split(",");
                int x = Integer.parseInt(arr[0]);
                int y = Integer.parseInt(arr[1]);

                Point point = new Point(x, y);
                list.add(point);
            }
            Point tmp = new Point(0, 0);
            list.add(tmp);

            Point[] result = getConvexPoint(list);
            int ans = 0;
            for (int i = 0; i < result.length; i++) {
                Point now = result[i];
                System.out.println(now.getX() + ", " + now.getY());
                ans += Math.abs(tmp.getX() - now.getX()) + Math.abs(tmp.getY() - now.getY());
                tmp = now;
            }

            System.out.println(ans);
        }
    }

    public static Point[] getConvexPoint(List<Point> list) {
        Point[] result = new Point[list.size()];
        int len = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (j == i)
                    continue;

                int[] judge = new int[list.size()];

                for (int k = 0; k < list.size(); k++) {
                    int a = list.get(j).getY() - list.get(i).getY();
                    int b = list.get(i).getX() - list.get(j).getX();
                    int c = (list.get(i).getX()) * (list.get(j).getY()) - (list.get(i).getY()) * (list.get(j).getX());

                    judge[k] = a * (list.get(k).getX()) + b * (list.get(k).getY()) - c;
                }

                if (JudgeArray(judge)) {
                    result[len++] = list.get(i);
                    break;
                }
            }
        }
        Point[] result1 = new Point[len];
        for (int m = 0; m < len; m++)
            result1[m] = result[m];
        return result1;
    }

    public static boolean JudgeArray(int[] Array) {
        boolean judge = false;
        int len1 = 0, len2 = 0;

        for (int i = 0; i < Array.length; i++) {
            if (Array[i] >= 0)
                len1++;
        }
        for (int j = 0; j < Array.length; j++) {
            if (Array[j] <= 0)
                len2++;
        }

        if (len1 == Array.length || len2 == Array.length)
            judge = true;
        return judge;
    }
}

class Point {
    private int x;
    private int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }
}
