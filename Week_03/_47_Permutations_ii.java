package com.xx.leetcode.week03.homework;

import java.util.*;

/**
 * 47. 全排列 II
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/2 19:58
 */
public class _47_Permutations_ii {
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        if(len < 1){
            return result;
        }
        Arrays.sort(nums);
        Deque<Integer> stack = new ArrayDeque<>();
        int[] used = new int[len];
        doPermuteUnique(nums,used,stack);
        return result;
    }

    private void doPermuteUnique(int[] nums, int[] used, Deque<Integer> stack) {
        int len = nums.length;
        // 结束条件
        if(stack.size() == len){
            result.add(new ArrayList<>(stack));
            return;
        }
        //
        for(int i = 0;i<len;i++){
            if(used[i] == 0){
                if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0) {
                    continue;
                }
                // 之前的路径，加上现在的路径
                stack.addLast(nums[i]);
                used[i] = 1;
                doPermuteUnique(nums,used,stack);
                // 回溯
                stack.removeLast();
                used[i] = 0;
            }
        }
    }
}
