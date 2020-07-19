package com.xx.leetcode.week09.homework;

import java.util.Stack;

/**
 * 32. 最长有效括号
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/19 10:26
 */
public class _32_LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int result = 0;
        // 始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    result = Math.max(result, i - stack.peek());
                }
            }
        }
        return result;
    }

    /**
     * dp
     * @param s
     * @return
     */
    public int longestValidParenthesesV1(String s) {
        int result = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    // 案例：()()
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                }else{
                    // 案例：(())
                    int previous = i - dp[i - 1] - 1;
                    if(previous >=0 && s.charAt(previous) == '('){
                        // 应对这种情况：()(())
                        int parentheses = i - dp[i - 1] - 2 > 0 ? dp[i - dp[i - 1] - 2] : 0;
                        dp[i] = dp[i - 1] + 2 + parentheses;
                    }
                }
                result = Math.max(result, dp[i]);
            }
        }
        return result;
    }
}
