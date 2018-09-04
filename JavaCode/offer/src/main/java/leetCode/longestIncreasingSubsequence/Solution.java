package leetCode.longestIncreasingSubsequence;

import java.util.Arrays;

public class Solution {
    // 第一个≥key
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

    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int len = 0;
        int[] ans = new int[nums.length];

        ans[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > ans[len]) {
                ans[++len] = nums[i];
            } else {
                ans[binarySearch(ans, 0, len, nums[i])] = nums[i];
            }
        }

        return len + 1;
    }

    public static void main(String[] args) {
        int[] arr = {10,9,2,5,3,4};
        System.out.println(lengthOfLIS(arr));
    }
}
