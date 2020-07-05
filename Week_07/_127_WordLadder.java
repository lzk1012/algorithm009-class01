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
    public static void main(String[] args) throws InterruptedException {
        new _127_WordLadder().ladderLength("a","c", Arrays.asList("a","b","c"));
        Thread.sleep(Integer.MAX_VALUE);
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

    /** 双向BFS */
    public int ladderLengthV2(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        // 使用两个集合作为扩散元素的容器，因为要快速判断集合中是否有某一元素，所以使用Set
        Set<String> beginSet = new HashSet<String>() {{
            add(beginWord);
        }};
        Set<String> endSet = new HashSet<String>() {{
            add(endWord);
        }};
        // 标准写法，一个已遍历元素的数组。
        Set<String> visited = new HashSet<String>() {{
            add(endWord);
            add(beginWord);
        }};
        int step = 1;
        // 二者为空，表示下一层次没有可遍历的元素，退出while，返回0
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // 使用较小的Set开始扩散
            if (endSet.size() < beginSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }
            // 上面已经保证beginSet较小，依次遍历。将扩散的元素添加至nextLevelSet，此Set用于下次遍历
            Set<String> nextLevelSet = new HashSet<>();
            for(String str:beginSet){
                char[] charArray = str.toCharArray();
                for(int i = 0;i<str.length();i++){
                    char originChar = charArray[i];
                    for(char replaceCh = 'a';replaceCh <= 'z';replaceCh++){
                        if(originChar == replaceCh){
                            continue;
                        }
                        charArray[i] = replaceCh;
                        String newStr = String.valueOf(charArray);
                        if(wordSet.contains(newStr)){
                            if(endSet.contains(newStr)){
                                return step + 1;
                            }
                            if(!visited.contains(newStr)){
                                visited.add(newStr);
                                nextLevelSet.add(newStr);
                            }
                        }
                    }
                    charArray[i] = originChar;
                }
            }
            step++;
            beginSet = nextLevelSet;
        }
        return 0;
    }
}
