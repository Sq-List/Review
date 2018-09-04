package dp;

import java.util.Scanner;

/**
 * 剪绳子问题
 * 给你一根长度为N的绳子，请把绳子剪成M段（m,n都是整数），
 * 每段绳子的长度记为k[0],k[1],k[2]...
 * 请问如何剪绳子使得k[0],k[1],k[2]...的乘积最大
 * 例如 绳子长度8 最大乘积18 = 2*3*3
 */
public class JianShengZi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N, M;

        while (in.hasNextInt()) {
            N = in.nextInt();
            M = in.nextInt();

            int[][] dp = new int[N + 1][M + 1];

            // 初始化
            for (int i = 1; i <= N; i++) {
                dp[i][1] = i;
            }

            for (int i = 2; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    int max = 0;
                    for (int k = 1; i - k >= j - 1; k++) {
                        if (dp[i - k][j - 1] * k > max) {
                            max = dp[i - k][j - 1] * k;
                        }
                    }
                    dp[i][j] = max;
                }
            }

            System.out.println(dp[N][M]);
        }
    }
}
