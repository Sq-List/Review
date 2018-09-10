package wangyi.autumn2018;

import java.util.Scanner;

public class Main2 {
    public static int lowbit(int x) {
        return -x & x;
    }

    public static void update(int[] a, int i, int v) {
        while (i < a.length) {
            a[i] += v;
            i += lowbit(i);
        }
    }

    public static int query(int[] a, int i) {
        int sum = 0;
        while (i > 0) {
            sum += a[i];
            i -= lowbit(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int k = in.nextInt();
        int ans = 0;
        int max = Integer.MIN_VALUE;

        int[] a = new int[n + 1];
        int[] t = new int[n + 1];
        int[] l = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            t[i] = in.nextInt();
            if (t[i] == 1) {
                ans += a[i];
                a[i] = 0;
            } else {
                update(l, i, a[i]);
            }
        }

        for (int i = 1; i <= n; i++) {
            if (t[i] == 0) {
                int up = i + k - 1 > n ? n : (i + k - 1);
                int sum = query(l, up) - query(l, i - 1);
                max = max > sum ? max : sum;
            }
        }

        System.out.println(ans + max);
    }
}
