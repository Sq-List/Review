package leetCode.twoSum;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums.length <= 2) {
            return new int[]{0, 1};
        }

        HashMap<Integer, Integer> indexMap = new HashMap<>(nums.length >> 1);

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int other = target - num;
            if (indexMap.containsKey(other)) {
                return new int[]{indexMap.get(other), i};
            } else {
                indexMap.put(num, i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 3, 7};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.twoSum(arr, 6)));
    }
}
