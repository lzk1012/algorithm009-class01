package com.xx.leetcode.week07.homework;

import java.util.HashSet;
import java.util.Set;

/**
 * 36. 有效的数独
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/2 13:14
 */
public class _36_ValidSudoku {
    public static void main(String[] args) {
        boolean flag = new _36_ValidSudoku().isValidSudoku(new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        });
        System.out.println(flag);
    }
    public boolean isValidSudoku(char[][] board) {
        if (board.length < 1 || board[0].length < 1) {
            return false;
        }
        int totalRow = board.length;
        int totalCol = board[0].length;
        int[][] rowArr = new int[totalRow][totalCol];
        int[][] colArr = new int[totalRow][totalCol];
        int[][] roomArr = new int[totalRow][totalCol];

        for (int i = 0; i < totalRow; i++) {
            for (int j = 0; j < totalCol; j++) {
                char element = board[i][j];
                if(element == '.'){
                    continue;
                }
                int eleIndex = element - '1';
                rowArr[i][eleIndex]++;
                colArr[j][eleIndex]++;
                int roomArrIndex = i / 3 * 3 + j / 3;
                roomArr[roomArrIndex][eleIndex]++;
                if (rowArr[i][eleIndex] > 1 || colArr[j][eleIndex] > 1 || roomArr[roomArrIndex][eleIndex] > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
