package com.xx.leetcode.week09.homework;

/**
 * 541. 反转字符串 II
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/15 13:27
 */
public class _541_ReverseString_ii {
    public static void main(String[] args) {
        new _541_ReverseString_ii().reverseStr("abcdefg",2);
    }
    public String reverseStr(String s, int k) {
        char[] a = s.toCharArray();
        int length = a.length;
        for (int start = 0; start < length; start += 2 * k) {
            int i = start;
            int j = Math.min(start + k - 1, length - 1);
            while (i < j) {
                char tmp = a[i];
                a[i++] = a[j];
                a[j--] = tmp;
            }
        }
        return new String(a);
    }
}
