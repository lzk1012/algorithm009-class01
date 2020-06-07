package com.xx.leetcode.week03.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/2 09:30
 */
public class _77_Combinations {
    List<List<Integer>> result;
    public List<List<Integer>> combine(int n, int k) {
        result = new ArrayList<>();
        // 特判
        if (n <= 0 || k <= 0 || n < k) {
            return result;
        }
        doCombine(n,k,1,new Stack<>());
        return result;
    }


    private void doCombine(int n, int k, int start, Stack<Integer> stack) {
        if(stack.size() == k){
            result.add(new ArrayList<>(stack));
            return;
        }
        // 第一次循环，从start开始，n结束，穷举出所有k个数的可能性
        // 下一次循环，从start+1开始...
//        for (int i = start;i<=n;i++){
        /*
        剪枝过程：
        如果 n = 15 ，k = 4，
        stack.size() == 1 的时候，接下来要选择 3 个元素，i 最大的值是 13，最后一个被选的是 [13,14,15]；
        stack.size() == 2 的时候，接下来要选择 2 个元素， i 最大的值是 14，最后一个被选的是 [14,15]；
        stack.size() == 3 的时候，接下来要选择 1 个元素， i 最大的值是 15，最后一个被选的是 [15]；
        因此，i的最大值max(i) = n - 接下来要选择的元素个数 + 1,
        即：i <= n - (k - stack.size()) + 1
         */
        for (int i = start;i <= n - (k - stack.size()) + 1;i++){
            stack.push(i);
            doCombine(n,k,i+1,stack);
            // stack引用传递，先把刚才加进来的值清空（刚才加的值的所有可能性，已经计算完成）
            stack.pop();
        }
    }
}
