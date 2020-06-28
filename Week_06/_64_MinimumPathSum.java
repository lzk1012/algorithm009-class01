package com.xx.leetcode.week06.daily;

import java.util.Arrays;

/**
 * 64. 最小路径和
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/22 13:54
 */
public class _64_MinimumPathSum {
    public static void main(String[] args) {
        new _64_MinimumPathSum().minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}});
    }

    public int minPathSum(int[][] grid) {
        int rowNum = grid.length;
        int colNum = grid[0].length;
        int[][] dp = new int[rowNum][colNum];
        dp[0][0] = grid[0][0];

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if(i == 0 && j == 0){
                    continue;
                }else if(i == 0){
                    dp[0][j] = grid[0][j] + dp[0][j - 1];
                }else if(j == 0){
                    dp[i][0] = grid[i][0] + dp[i - 1][0];
                }else{
                    int minValue = Math.min(dp[i][j - 1], dp[i - 1][j] );
                    dp[i][j] = minValue + grid[i][j];
                }
            }
        }
        return dp[rowNum - 1][colNum - 1];
    }
}
