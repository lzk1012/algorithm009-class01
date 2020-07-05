package com.xx.leetcode.week01;

/**
 * TODO 类描述信息
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/5/25 14:41
 */
public class _70_ClimbingStairs {
    public static void main(String[] args) {
        new _70_ClimbingStairs().climbStairs(2);
    }
    public int climbStairs(int n) {
        if(n < 0){
            return 0;
        }else if(n <= 1){
            return 1;
        }
        int[] climbWays = new int[n+1];
        climbWays[0] = climbWays[1] = 1;
        for(int i = 2;i<=n;i++){
            climbWays[i] = climbWays[i-1] + climbWays[i-2];
        }
        return climbWays[n];
    }

}
