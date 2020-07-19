package com.xx.leetcode.week09.homework;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 557. 反转字符串中的单词 III
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/19 14:34
 */
public class _557_ReverseWordsInAString_iii {
    public static void main(String[] args) {
        new _557_ReverseWordsInAString_iii().reverseWords("Let's take LeetCode contest");
    }

    /**
     * 使用双端队列辅助实现
     * @param s
     * @return
     */
    public String reverseWordsV1(String s) {
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder sb= new StringBuilder();
        for(char ch : s.toCharArray()){
            if(sb.length() > 0 && ch == ' '){
                stack.offerLast(sb.toString());
                sb.setLength(0);
            }else if(ch != ' '){
                sb.insert(0,ch);
            }
        }
        stack.offerLast(sb.toString());
        return String.join(" ", stack);
    }

    /**
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        char[] cs = s.toCharArray();
        // 开始下标
        int startIndex = 0;
        // 空格下标
        int blankIndex = 0;
        while (blankIndex < cs.length) {
            while (blankIndex < cs.length && cs[blankIndex] != ' ') {
                blankIndex++;
            }
            reverse(cs, startIndex, blankIndex - 1);
            startIndex = blankIndex + 1;
            blankIndex = blankIndex + 1;

        }
        return new String(cs);
    }

    public void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
    }
}
