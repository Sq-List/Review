package longestIncreasingSubsequence.openJBailian2945;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static int LIS(int[] arr) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] <= arr[i]) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }

        int len = -1;
        for (int i = 0; i < arr.length; i++) {
            len = Math.max(len, dp[i]);
        }
        return len;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        int[] arr = new int[k];

        for (int i = 0; i < k; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(LIS(arr));
    }
}
