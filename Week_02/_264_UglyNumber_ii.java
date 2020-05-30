package com.xx.leetcode.week02.homework;

/**
 * 264. 丑数 II
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/5/29 14:04
 */
public class _264_UglyNumber_ii {
    public static void main(String[] args) {
        new _264_UglyNumber_ii().nthUglyNumber(2);
    }

    public int nthUglyNumber(int n) {
        if(n < 1){
            return 0;
        }
        int[] numArr = new int[n];
        numArr[0] = 1;
        /*
        这三个指针的作用举例：（原文链接：https://leetcode-cn.com/problems/ugly-number-ii/solution/san-zhi-zhen-fang-fa-de-li-jie-fang-shi-by-zzxn/）
            一开始，丑数只有{1}
                此时1可以同2，3，5相乘，取最小的值1×2=2，此结果为下一个丑数。
            现在丑数中有{1，2}
                在上一步中，1已经同2相乘过了，所以今后没必要再比较1×2了，也就是说1失去了同2相乘的资格。
                而新加入的元素2，可以相乘的数也只有刚才计算过的2
                所以我们只需要比较1×3，1×5，2×2。
            依此类推，每次我们都分别比较 【有资格同2，3，5相乘的数，和2，3，5相乘】 得到的三个结果，选择最小的那个作为下一个丑数，如果下一个丑数是通过乘2得到的，就把2的指针加一
        动态规划的方法，最主要还是要找到重叠子问题，即
            int nextUglyNum = Math.min(Math.min(numArr[index2] * 2,numArr[index3] * 3),numArr[index5] * 5);
         */
        int index2 = 0,index3 = 0,index5 = 0;
        for(int i = 1;i<n;i++){
            int nextUglyNum = Math.min(Math.min(numArr[index2] * 2,numArr[index3] * 3),numArr[index5] * 5);
            numArr[i] = nextUglyNum;
            if(nextUglyNum == numArr[index2] * 2) index2++;
            if(nextUglyNum == numArr[index3] * 3) index3++;
            if(nextUglyNum == numArr[index5] * 5) index5++;
        }
        return numArr[n-1];
    }
}
