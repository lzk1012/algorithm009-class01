package com.xx.leetcode.week06.daily;

/**
 * 44. 通配符匹配
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/23 20:18
 */
public class _44_WildcardMatching {
    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        // p为空，s除了空其他情况都定不匹配。当s为空的时候，看p是不是*
        for (int i = 1; i <= pLen; i++) {
            dp[0][i] = p.charAt(i - 1) == '*' && dp[0][i - 1];
        }
        for (int row = 1; row <= sLen; row++) {
            for (int col = 1; col <= pLen; col++) {
                char charP = p.charAt(col - 1);
                if (charP == '?' || charP == s.charAt(row - 1)) {
                    dp[row][col] = dp[row - 1][col - 1];
                }else if(charP == '*'){
                    dp[row][col] = dp[row - 1][col] || dp[row][col - 1];
                }
            }
        }
        return dp[sLen][pLen];
    }
}
