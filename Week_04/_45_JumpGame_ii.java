package com.xx.leetcode.week04.homework;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 45. 跳跃游戏 II
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/12 20:40
 */
public class _45_JumpGame_ii {
    /**
     * 不可达返回 0
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 0;
        for (int i = 1, last = 0; i < len; i++) {
            // last指向第一个能能够到达i的点
            while (last < len && last + nums[last] < i) last++;
            dp[i] = dp[last] + 1; // 使用第一个能到i的点更新f[i]
        }
        return dp[len - 1];
    }
}
