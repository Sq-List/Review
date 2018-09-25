package maximumContinuousSubsequenceSum;

import java.util.Arrays;

/**
 * 最大连续子序列的和 O(n)
 * 步骤 1：令状态 dp[i] 表示以 A[i] 作为末尾的连续序列的最大和（这里是说 A[i] 必须作为连续序列的末尾）。
 * 步骤 2：做如下考虑：因为 dp[i] 要求是必须以 A[i] 结尾的连续序列，那么只有两种情况：
 *      这个最大和的连续序列只有一个元素，即以 A[i] 开始，以 A[i] 结尾。
 *      这个最大和的连续序列有多个元素，即从前面某处 A[p] 开始 (p<i)，一直到 A[i] 结尾。
 * 对第一种情况，最大和就是 A[i] 本身。
 * 对第二种情况，最大和是 dp[i-1]+A[i]。
 */
public class MaximumContinuousSubsequenceSum {
    public static int MCSS(int[] arr) {
        int[] dp = new int[arr.length];

        int ans = Integer.MIN_VALUE;
        dp[0] = arr[0];
        int start = 0;
        int end = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > dp[i - 1] + arr[i]) {
                dp[i] = arr[i];
                start = i;
            } else {
                dp[i] = dp[i - 1] + arr[i];
            }

            if (dp[i] > ans) {
                ans = dp[i];
                end = i;
            }
        }

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(dp));
        System.out.println("start = " + start + ", end = " + end);

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 11, -4, 13, -5, -2};
        System.out.println(MCSS(arr));
    }
}
