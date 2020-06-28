package com.xx.leetcode.week06.daily;

/**
 * 363. 矩形区域不超过 K 的最大数值和
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/27 10:31
 */
public class _363_MaxSumOfRectangleNoLargerThanK {
    public static void main(String[] args) {
        new _363_MaxSumOfRectangleNoLargerThanK().maxSumSubmatrixV2(new int[][]{{5,-4,-3,4},{-3,-4,4,5},{5,1,5,-4}},3);
    }
    /**
     * 解法1：算出所有可能的矩阵和，找到一个不大于k的最大值。时间复杂度O(m*n*m*n)【m，n分别代表行列数】
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int result = Integer.MIN_VALUE;
        int rowNum = matrix.length;
        int colNum = matrix[0].length;

        for (int i1 = 1; i1 <= rowNum; i1++) {
            for (int j1 = 1; j1 <= colNum; j1++) {
                // i1，j1分别表示固定的起点，下面的循环标识以（i1,j1）为起点的所有的矩阵和
                int[][] dp = new int[rowNum + 1][colNum + 1]; // renew  // from (i1,j1) to (i2,j2)
                dp[i1][j1] = matrix[i1 - 1][j1 - 1];
                for (int i2 = i1; i2 <= rowNum; i2++) {
                    for (int j2 = j1; j2 <= colNum; j2++) {
                        // 动态转移方程：(i2,j2)左侧的和+上面的和-重叠区域的和+(i2,j2)点本身
                        dp[i2][j2] = dp[i2 - 1][j2] + dp[i2][j2 - 1] - dp[i2 - 1][j2 - 1] + matrix[i2 - 1][j2 - 1];
                        if (dp[i2][j2] <= k && dp[i2][j2] > result) result = dp[i2][j2];
                    }
                }
            }
        }
        return result;
    }

    /**
     * 解法2：用第一列的值，初始化一个一维数组，而后遍历后续列，将值对应添加至数组中，然后对找到数组中不大于K的最大值。时间复杂度O(m*n*m*n)
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrixV2(int[][] matrix, int k) {
        int result = Integer.MIN_VALUE;
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        for (int i = 0; i < colNum; i++) {
            int[] arr = new int[rowNum];
            for (int c = i; c < colNum; c++) {
                for (int j = 0; j < rowNum; j++) {
                    arr[j] += matrix[j][c];
                }
                // 求数组中连续和不大于k的最大值
                int max = Integer.MIN_VALUE;
                for (int m = 0; m < rowNum; m++) {
                    int sum = 0;
                    for (int n = m; n < rowNum; n++) {
                        sum += arr[n];
                        if (sum > max && sum <= k) max = sum;
                    }
                }
                result = Math.max(max, result);

            }
        }
        return result;
    }
}
