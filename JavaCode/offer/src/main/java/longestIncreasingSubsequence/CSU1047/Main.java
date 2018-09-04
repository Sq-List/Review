package longestIncreasingSubsequence.CSU1047;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
//    public static int LIS(int[] arr) {
//        int[] dp = new int[arr.length];
//        Arrays.fill(dp, 1);
//
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[j] > arr[i]) {
//                    dp[j] = Math.max(dp[j], dp[i] + 1);
//                }
//            }
//        }
//
//        int len = -1;
//        for (int i = 0; i < arr.length; i++) {
//            len = Math.max(len, dp[i]);
//        }
//        return len;
//    }

    // 返回 第一个≥key 的元素的下标
    public static int binarySearch(int[] arr, int left, int right, int key) {
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static int LIS(int[] arr) {
        int len = 0;
        int[] ans = new int[arr.length];

        ans[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > ans[len]) {
                ans[++len] = arr[i];
            } else {
                ans[binarySearch(ans, 0, len, arr[i])] = arr[i];
            }
        }

        return len + 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            int k = in.nextInt();
            int[] arr = new int[k];

            for (int i = 0; i < k; i++) {
                arr[i] = in.nextInt();
            }

            System.out.println(LIS(arr));
        }

    }
}
