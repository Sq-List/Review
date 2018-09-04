package wangyi.spring2018;

import java.util.Scanner;

/**
 * 牛牛的背包问题
 */
public class KnapsackProblem {
    private static long ans = 0;

    // t 代表下标
    public static void dfs(long t, long weight, long w, long[] v) {
        ans++;
        if (t == v.length - 1) {
            return;
        }

        for (long i = t + 1; i < v.length; i++) {
            if (weight + v[(int)i] <= w) {
                dfs(i, weight + v[(int)i], w, v);
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            ans = 0;
            long n = in.nextLong();
            long w = in.nextLong();

            long sum = 0;
            long[] v = new long[(int)n];
            for (int i = 0; i < n; i++) {
                v[i] = in.nextInt();
                sum += v[i];
            }

            if (sum <= w) {
                ans = 1 << n;
            } else {
                dfs(-1, 0, w, v);
            }

            System.out.println(ans);
        }
    }
}
