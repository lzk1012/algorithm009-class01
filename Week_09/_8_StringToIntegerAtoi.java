package com.xx.leetcode.week09.homework;

/**
 * 8. 字符串转换整数 (atoi)
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/19 14:12
 */
public class _8_StringToIntegerAtoi {
    public int myAtoi(String str) {
        str = str.trim();
        if (str.length() == 0) return 0;
        char ch0 = str.charAt(0);
        if (!Character.isDigit(ch0) && ch0 != '-' && ch0 != '+')
            return 0;
        long ans = 0L;
        // 是否负数
        boolean neg = ch0 == '-';
        int startIndex = !Character.isDigit(ch0) ? 1 : 0;
        while (startIndex < str.length() && Character.isDigit(str.charAt(startIndex))) {
            ans = ans * 10 + (str.charAt(startIndex++) - '0');
            if (!neg && ans > Integer.MAX_VALUE) {
                ans = Integer.MAX_VALUE;
                break;
            }
            if (neg && ans > 1L + Integer.MAX_VALUE) {
                ans = 1L + Integer.MAX_VALUE;
                break;
            }
        }
        return neg ? (int) -ans : (int) ans;
    }
}
