package com.xx.leetcode.week05.daily;

/**
 * 5. 最长回文子串
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/22 12:23
 */
public class _5_LongestPalindromicSubstring {
    public static void main(String[] args) {
        new _5_LongestPalindromicSubstring().longestPalindrome("cbbd");
    }

    public String longestPalindrome(String s) {
        // 特判
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j]) {
                    int thisLength = j - i + 1;
                    if (thisLength > maxLen) {
                        maxLen = j - i + 1;
                        begin = i;
                    }
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
