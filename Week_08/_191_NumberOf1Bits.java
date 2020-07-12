package com.xx.leetcode.week08.homework;

/**
 * 191. 位1的个数
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/7 18:19
 */
public class _191_NumberOf1Bits {
    public int hammingWeight(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if((n & 1) == 1){
                result++;
            }
            n = n>>1;
        }
        return result;
    }

    /**
     * x = x & (x-1)清零最低位的1
     * @param n
     * @return
     */
    public int hammingWeightV2(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }

}
