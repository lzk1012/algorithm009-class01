package com.xx.leetcode.week06.daily;

import com.xx.leetcode.support.Trie;

import java.util.*;

/**
 * 212. 单词搜索 II
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/28 15:41
 */
public class _212_WordSearch_ii {
    private int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public List<String> findWords(char[][] board, String[] words) {
        // 将要查找的word都加入字典树中
        Trie trie = new Trie();
        for (String str : words) {
            trie.insert(str);// 这一步骤的时间复杂度为O(s*l)：s代表words元素个数，l代表元素长度
        }
        // 遍历board，找到所有可能的组合字符串，判断是否在字典树中
        int rowNum = board.length;
        int colNum = board[0].length;
        boolean[][] used = new boolean[rowNum][colNum];
        Set<String> result = new HashSet<>();
        //遍历整个二维数组
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 这一步的时间复杂度为O(M*N*4^L)：M、N代表二维网格宽高，L代表单词的最大长度
                dfs(board, used, i, j, rowNum, colNum, result, trie);
            }
        }
        return new ArrayList<>(result);
    }

    private void dfs(char[][] board, boolean[][] used, int i, int j, int rowNum, int colNum, Set<String> result, Trie cur) {
        // 判断是否越界、是否已经使用过
        if (i < 0 || i >= rowNum || j < 0 || j >= colNum || used[i][j]){
            return;
        }
        cur = cur.tries[board[i][j] - 'a'];
        if (cur == null) {
            // 字典树中不存在当前字符对应的前缀了
            return;
        }else if (cur.isEnd) {
            result.add(cur.leafValue);
            //找到单词后不能回退，字典树中可能存在“ad” “addd”这样的单词，因此需要继续回溯
//            return;
        }
        used[i][j] = true;
        for (int[] direction : directions) {
            dfs(board, used, i + direction[0], j + direction[1], rowNum, colNum, result, cur);
        }
        //回溯
        used[i][j] = false;
    }
}