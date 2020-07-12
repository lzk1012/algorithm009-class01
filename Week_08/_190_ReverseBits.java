package com.xx.leetcode.week08.homework;

/**
 * 190. 颠倒二进制位
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/7 19:02
 */
public class _190_ReverseBits {
    public static void main(String[] args) {
        new _190_ReverseBits().reverseBits(15);
    }
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++,n = n >> 1) {
            if ((n & 1) == 1) {
                int shift = 32 - i - 1;
                result = result | (1 << shift);
            }
        }
        return result;
    }
}
