package wangyi.autumn2018;

import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        int max = a * b * c;

        int tmp = (a + b) * c;
        max = tmp > max ? tmp : max;

        tmp = a * (b + c);
        max = tmp > max ? tmp : max;

        tmp = a + b + c;
        max = tmp > max ? tmp : max;

        System.out.println(max);
    }
}
