package com.xx.leetcode.week04.homework;

import java.util.*;

/**
 * 127. 单词接龙
 * 这题求的是最短路径！！
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/8 19:55
 */
public class _127_WordLadder {
    public static void main(String[] args) {
        new _127_WordLadder().ladderLength("a","c", Arrays.asList("a","b","c"));
    }

    /**
     * 单项广度优先遍历
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        Set<String> wordSet = new HashSet<>(wordList);
        if(wordSet.size() < 1 || !wordSet.contains(endWord)){
            return 0;
        }
        // 广度优先遍历，都是基于队列实现的
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        wordSet.remove(beginWord);
        // 包含起点，因此初始化的时候步数为 1
        int step = 1;
        while(!queue.isEmpty()){
            step++;
            int crtSize = queue.size();
            for(int i = 0;i<crtSize;i++){
                char[] charArray =  queue.poll().toCharArray();
                for(int j = 0;j<charArray.length;j++){
                    char originChar = charArray[j];
                    // 采用单个字符从a-z的顺序比较，时间复杂度为O(26*wordLen)，其中常数26可省略。如果是逐个比较wordList，时间复杂度太高：O(N*wordLen) N是wordList大小，wordLen是单个字符串长度
                    for(char k = 'a'; k<= 'z'; k++){
                        if(originChar == k)continue;
                        charArray[j] = k;
                        String newStr = String.valueOf(charArray);
                        if(wordSet.contains(newStr)){
                            if(newStr.equals(endWord)){
                                return step;
                            }
                            queue.add(newStr);
                            wordSet.remove(newStr);
                        }
                    }
                    charArray[j] = originChar;
                }
            }
        }
        return 0;
    }

}
