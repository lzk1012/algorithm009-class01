package com.xx.leetcode.week03.homework;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N皇后
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/3 16:07
 */
public class _51_NQueens {
    public static void main(String[] args) {
        new _51_NQueens().solveNQueens(4);
    }

    /**
     * 三个数组，用来标记 列上、左上斜边、右上斜边 是否已有皇后
     */
    boolean[] column;
    boolean[] leftTop;
    boolean[] rightTop;

    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // 棋盘
        int[][] board = new int[n][n];
        column = new boolean[n];
        leftTop = new boolean[n << 2 -1];
        rightTop = new boolean[leftTop.length];

        doSolveNQueens(board,0);
        return result;
    }

    /**
     * 在startRow行添加一个皇后
     * @param board
     * @param startRow
     */
    private void doSolveNQueens(int[][] board,int startRow) {
        int len = board.length;
        if(startRow == len){
            List<String> strList = transfer2String(board);
            result.add(strList);
            return;
        }
        for(int j = 0;j<board[startRow].length;j++){
            if(column[j]) continue;
            int leftTopIndex = j - startRow + len -1;
            if(leftTop[leftTopIndex]) continue;
            int rightTopIndex = j + startRow;
            if(rightTop[rightTopIndex]) continue;

            column[j] = true;
            leftTop[leftTopIndex] = true;
            rightTop[rightTopIndex] = true;
            board[startRow][j] = 1;
            doSolveNQueens(board,startRow + 1);
            board[startRow][j] = 0;
            column[j] = false;
            leftTop[leftTopIndex] = false;
            rightTop[rightTopIndex] = false;
        }
    }

    private List<String> transfer2String(int[][] board){
        List<String> result = new ArrayList<>();
        // 外层行
        for(int i = 0;i<board.length;i++){
            StringBuilder sb = new StringBuilder();
            boolean thisRowHasElement = false;
            // 内层列
            for(int j = 0;j<board[i].length;j++){
                if(board[i][j] == 1){
                    thisRowHasElement = true;
                    sb.append("Q");
                }else{
                    sb.append(".");
                }
            }
            if(thisRowHasElement){
                result.add(sb.toString());
            }
        }
        return result;
    }

    //是否可以在 board[row][col] 放置皇后
    private boolean isValid(int[][] board,int row, int col) {
        // 检查列是否有皇后互相冲突
        for (int i = 0; i < row; ++i) {
            if (board[i][col] == 1)
                return false;
        }
        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; --i, --j) {
            if (board[i][j] == 1)
                return false;
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; --i, ++j) {
            if (board[i][j] == 1)
                return false;
        }
        return true;
    }
}
