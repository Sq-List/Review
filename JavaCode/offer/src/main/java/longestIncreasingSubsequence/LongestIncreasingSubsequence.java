package longestIncreasingSubsequence;

import java.util.Arrays;
import java.util.Stack;

/**
 * 最长上升子序列
 */
public class LongestIncreasingSubsequence {
    public static int LIS(int[] arr) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);

//        int len = -1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

//        Stack<Integer> ansStack = new Stack<>();

        int len = -1;
        for (int i = 0; i < arr.length; i++) {
            len = Math.max(len, dp[i]);
        }
//        int ans = len;
//        for (int i = arr.length - 1; i >= 0; i--) {
//            if (len == dp[i]) {
//                ansStack.push(arr[i]);
//                len --;
//            }
//        }
//        while (!ansStack.empty()) {
//            System.out.print(ansStack.pop() + " ");
//        }
//        System.out.println();

        return len;
    }

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

    // O(nlogn)
    public static int LIS2(int[] arr) {
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
        int[] arr = {7, 9, 1, 5, 6, 2, 8, 7, 9};
        System.out.println(LIS(arr));
//        System.out.println(LIS2(arr));
    }
}
