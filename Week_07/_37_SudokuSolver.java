package com.xx.leetcode.week07.homework;

/**
 * 37. 解数独
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/2 14:21
 */
public class _37_SudokuSolver {
    public void solveSudoku(char[][] board) {
        // 三个布尔数组 表明 行, 列, 还有 3*3 的方格的数字是否被使用过
        boolean[][] rowUsed = new boolean[9][10];
        boolean[][] colUsed = new boolean[9][10];
        boolean[][] room = new boolean[9][10];
        // 初始化
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col++) {
                int num = board[row][col] - '0';
                if(1 <= num && num <= 9){
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    int roomArrIndex = row / 3 * 3 + col / 3;
                    room[roomArrIndex][num] = true;
                }
            }
        }
        // 递归尝试填充数组
        recusiveSolveSudoku(board, rowUsed, colUsed, room, 0);
    }

    private boolean recusiveSolveSudoku(char[][]board, boolean[][]rowUsed, boolean[][]colUsed, boolean[][]roomUsed, int startIndex){
        // 边界校验, 如果已经填充完成, 返回true, 表示一切结束
        if(startIndex == 81){
            return true;
        }
        int col = startIndex % 9;
        int row = startIndex / 9;
        // 是空则尝试填充, 否则跳过继续尝试填充下一个位置
        if(board[row][col] == '.') {
            int roomArrIndex = row / 3 * 3 + col / 3;
            // 尝试填充1~9
            for(int num = 1; num <= 9; num++){
                boolean canUsed = !(rowUsed[row][num] || colUsed[col][num] || roomUsed[roomArrIndex][num]);
                if(canUsed){
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    roomUsed[roomArrIndex][num] = true;

                    board[row][col] = (char)('0' + num);
                    if(recusiveSolveSudoku(board, rowUsed, colUsed, roomUsed, startIndex + 1)){
                        return true;
                    }
                    board[row][col] = '.';

                    rowUsed[row][num] = false;
                    colUsed[col][num] = false;
                    roomUsed[roomArrIndex][num] = false;
                }
            }
        } else {
            return recusiveSolveSudoku(board, rowUsed, colUsed, roomUsed, startIndex + 1);
        }
        return false;
    }
}