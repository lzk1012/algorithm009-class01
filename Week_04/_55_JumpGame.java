package com.xx.leetcode.week04.homework;

/**
 * 55. 跳跃游戏
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/12 16:09
 */
public class _55_JumpGame {
    public static void main(String[] args) {
        new _55_JumpGame().canJump(new int[]{1, 0, 4});
    }

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length < 1) {
            return false;
        }
        int len = nums.length;
        boolean[] dp = new boolean[len];
        dp[0] = true;
        for (int i = 1; i < len; i++) {
            int step = 1;
            for (int j = i - step; j >= 0; j--) {
                dp[i] = dp[j] && nums[j] >= step;
                // dp[i]可达，继续下一个
                if (dp[i]) {
                    break;
                }
                step++;
            }
        }
        return dp[len - 1];
    }

}
