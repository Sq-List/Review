package binaryIndexedTree.hdu1541;

import java.util.Scanner;

public class Main {
    public static int lowbit(int x) {
        return x & -x;
    }

    public static void update(int[] c, int i, int val) {
        while (i < c.length) {
            c[i] += val;
            i += lowbit(i);
        }
    }

    public static int sum(int[] c, int i) {
        int rec = 0;
        while (i > 0) {
            rec += c[i];
            i -= lowbit(i);
        }
        return rec;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int[] a = new int[n];
            int[] ans = new int[n];

            int max = -1;
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt() + 1;
                in.nextInt();

                max = Math.max(max, a[i]);
            }

            int[] c = new int[max + 1];
            for (int i = 0; i < n; i++) {
                ans[sum(c, a[i])]++;
                update(c, a[i], 1);
            }

            for (int tmp : ans) {
                System.out.println(tmp);
            }
        }
    }
}
