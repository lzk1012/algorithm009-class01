package com.xx.leetcode.week08.homework;

/**
 * 231. 2的幂
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/7 18:54
 */
public class _231_PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        // 一个数如果是2的幂，则二进制表示中只有一个1
        return n > 0 && (n & (n - 1)) == 0;
    }
}
