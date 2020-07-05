package com.xx.leetcode.week07.homework;

import com.xx.leetcode.support.UnionFind;

/**
 * 130. 被围绕的区域
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/30 11:06
 */
public class _130_SurroundedRegions {

    private final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    /**
     * dfs方式实现：
     *
     * @param board
     */
    public void solveV1(char[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length;
        int n = board[0].length;
        /*
        先将边缘的O及关联的O改成其他字符。
        此时board中有三个元素，针对三个情况再次遍历：
            X：忽略
            O：代表被X围绕的O，改成X即可
            #：边界上的O变更过来的，代表没有被X围绕，再变成O即可
         */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 从边缘o开始搜索
                boolean isEdge = i == 0 || j == 0 || i == m - 1 || j == n - 1;
                if (isEdge && board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }
        // 第二次遍历
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#') {
            // board[i][j] == '#' 说明已经搜索过了.
            return;
        }
        board[i][j] = '#';
        // 递归周围
        for (int[] crtDirection : directions) {
            dfs(board, i + crtDirection[0], j + crtDirection[1]);
        }
    }

    /**
     * 并查集方式
     *
     * @param board
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int totalRow = board.length;
        int totalCol = board[0].length;
        // 创建并查集，大小为二维数组的元素个数+1，多出来的一个节点，存放一个不存在的元素
        UnionFind uf = new UnionFind(totalCol * totalRow + 1);
        int fakeNode = totalCol * totalRow;
        for (int i = 0; i < totalRow; i++) {
            for (int j = 0; j < totalCol; j++) {
                if (board[i][j] == 'O') {
                    int thisCoor = transferCoordinate(i, j, totalCol);
                    // 如果O元素是边界上的，和前面的fakeNode汇合
                    if (i == 0 || i == totalRow - 1 || j == 0 || j == totalCol - 1) {
                        uf.union(thisCoor, fakeNode);
                    } else {
                        // 非边界上的O元素，汇合周围的O（如果有和边界O连通的，自然就跑一块去了）
                        for (int[] crtDirection : directions) {
                            int newX = i + crtDirection[0];
                            int newY = j + crtDirection[1];
                            if (newX >= 0 && newX < totalRow && newY >= 0 && newY < totalCol && board[newX][newY] == 'O') {
                                uf.union(thisCoor,transferCoordinate(newX,newY,totalCol));
                            }
                        }
                    }

                }
            }
        }
        for (int i = 0; i < totalRow; i++) {
            for (int j = 0; j < totalCol; j++) {
                // 如果不跟fakeNode同处一片区域，就是x或者被包围的o，总之都要改成X
                if (!uf.isConnected(transferCoordinate(i, j,totalCol), fakeNode)) {
                    board[i][j] = 'X';
                }
            }
        }
    }
    private int transferCoordinate(int row, int col, int totalCol) {
        return row * totalCol + col;
    }
}
