package com.xx.leetcode.week09.homework;

/**
 * 917. 仅仅反转字母
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/19 14:56
 */
public class _917_ReverseOnlyLetters {
    public String reverseOnlyLetters(String S) {
        StringBuilder ans = new StringBuilder();
        int j = S.length() - 1;
        for (int i = 0; i < S.length(); ++i) {
            if (Character.isLetter(S.charAt(i))) {
                while (!Character.isLetter(S.charAt(j)))
                    j--;
                ans.append(S.charAt(j--));
            } else {
                ans.append(S.charAt(i));
            }
        }

        return ans.toString();
    }
}
