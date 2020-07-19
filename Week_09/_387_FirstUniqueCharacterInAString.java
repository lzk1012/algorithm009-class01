package com.xx.leetcode.week09.daily;

/**
 * 387. 字符串中的第一个唯一字符
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/13 11:31
 */
public class _387_FirstUniqueCharacterInAString {
    public static void main(String[] args) {
        new _387_FirstUniqueCharacterInAString().firstUniqChar("loveleetcode");
    }

    public int firstUniqChar(String s) {
        int[] letter = new int[26];
        for (char ch : s.toCharArray()) {
            letter[ch - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (letter[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
