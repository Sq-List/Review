package wangyi.spring2018;

import java.util.Scanner;

/**
 * 数对
 */
public class PairNumbers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            long n = in.nextLong();
            long k = in.nextLong();

            long ans = 0;
            if (k == 0) {
                ans = n * n;
            } else {
                for (long y = k + 1; y <= n; y++) {
                    // 对于一个确定的y，x%y 有周期性
                    ans += n / y * (y - k) + (n % y >= k ? n % y - k + 1 : 0);
                }
            }

            System.out.println(ans);
        }
    }
}
