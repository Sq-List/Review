package wangyi.autumn2018;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        int[] a = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int tmp = in.nextInt();
            a[tmp]++;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            min = min < a[i] ? min : a[i];
        }

        System.out.println(min);
    }
}
