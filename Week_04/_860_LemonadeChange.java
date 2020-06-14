package com.xx.leetcode.week04.homework;

/**
 * 860. 柠檬水找零
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/11 14:52
 */
public class _860_LemonadeChange {
    public static void main(String[] args) {
        new _860_LemonadeChange().lemonadeChange(new int[]{  5,5,5,5,10,5,10,10,10,20 });
    }
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill: bills) {
            // 5的时候，five++，continue
            // 10的时候，five--，如果five小于零，return false;ten++,continue
            // 20的时候，要减15，从10开始减
            switch (bill){
                case 5:
                    five++;
                    continue;
                case 10:
                    if(--five < 0){
                        return false;
                    }
                    ten++;
                    continue;
                case 20:
                    if (five > 0 && ten > 0) {
                        five--;
                        ten--;
                    } else if (five >= 3) {
                        five -= 3;
                    } else {
                        return false;
                    }
            }
        }
        return true;
    }

}
