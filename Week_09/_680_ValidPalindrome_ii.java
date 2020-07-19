package com.xx.leetcode.week09.daily;

/**
 * 680. 验证回文字符串 Ⅱ
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/18 13:30
 */
public class _680_ValidPalindrome_ii {
    public static void main(String[] args) {
        String str = "123";
        System.out.println(str.substring(0,str.length()));
    }
    private boolean hasDel = false;
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while(left < right){
            if(s.charAt(left) == s.charAt(right)){
                left++;
                right--;
            }else{
                String delRight = s.substring(left, right);// 删除右边一个字符
                String delLeft = s.substring(left + 1, right + 1);// 删除左边一个字符
                if(hasDel){
                    return false;
                }else{
                    hasDel = true;
                    return validPalindrome(delRight) || validPalindrome(delLeft);
                }
            }
        }
        return true;
    }
}
