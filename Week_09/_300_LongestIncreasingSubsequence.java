package com.xx.leetcode.week09.daily;


import java.util.*;

/**
 * 300. 最长上升子序列
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/16 21:41
 */
public class _300_LongestIncreasingSubsequence {
    public static void main(String[] args) {
        new _300_LongestIncreasingSubsequence().lengthOfLIS(new int[]{0});
    }

    public int lengthOfLIS(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        Arrays.fill(dp, 1);
        int result = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
