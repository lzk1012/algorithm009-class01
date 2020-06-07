package com.xx.leetcode.week03.homework;

import java.util.*;

/**
 * 46. 全排列
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/2 12:59
 */
public class _46_Permutations {
    // 使用一个动态数组保存所有可能的全排列
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        // 首先是特判
        if(len < 1){
            return result;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        // 使用一个数组表示元素是否使用过，在剪枝的时候使用
        int[] used = new int[len];
        doPermute(nums,used,stack);
        return result;
    }

    private void doPermute(int[] nums,int[] used,Deque<Integer> stack) {
        int len = nums.length;
        if(stack.size() == len){
            // 变量stack引用传递
            result.add(new ArrayList<>(stack));
            return;
        }
        for(int i = 0;i<len;i++){
            if(used[i] == 0){
                stack.addLast(nums[i]);
                used[i] = 1;
                doPermute(nums,used,stack);
                // 回溯。代码在形式上和递归之前是对称的
                used[i] = 0;
                stack.removeLast();
            }
        }
    }
}
