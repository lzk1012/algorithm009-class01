package com.xx.leetcode.week02.homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 242. 有效的字母异位词
 * 异位词：单词中各个字母出现的次数相同，但是位置不同
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/5/26 15:07
 */
public class _242_ValidAnagram {

    public static void main(String[] args) {

    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'a';
            table[index]--;
            if (table[index] < 0) {
                return false;
            }
        }
        return true;
    }
}
