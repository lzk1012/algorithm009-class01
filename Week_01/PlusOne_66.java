package com.xx.local;

/**
 * TODO 类描述信息
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/5/24 19:15
 */
public class PlusOne_66 {

    /**
     * 66. 加一
     * @param digits 非空数组
     * @return 加一之后的返回
     */
    public int[] plusOne(int[] digits) {
        int carry = 1;// 进位
        for(int i = digits.length -1 ;i>=0;i--){
            int value = digits[i] + carry;
            digits[i] = value % 10;
            carry = value / 10;
        }
        if(carry == 1){
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }else{
            return digits;
        }
    }
}
