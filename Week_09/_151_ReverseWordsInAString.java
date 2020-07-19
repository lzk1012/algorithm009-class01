package com.xx.leetcode.week09.homework;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 151. 翻转字符串里的单词
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/19 14:26
 */
public class _151_ReverseWordsInAString {
    public static void main(String[] args) {
        new _151_ReverseWordsInAString().reverseWords("a good   example");
    }
    public String reverseWords(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') ++left;

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') --right;

        Deque<String> d = new ArrayDeque();
        StringBuilder word = new StringBuilder();

        while (left <= right) {
            char c = s.charAt(left);
            if ((word.length() != 0) && (c == ' ')) {
                // 将单词 push 到队列的头部
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        d.offerFirst(word.toString());

        return String.join(" ", d);
    }
}
