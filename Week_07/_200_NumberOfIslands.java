package com.xx.leetcode.week04.homework;


import com.xx.leetcode.support.UnionFind;

import java.util.LinkedList;

/**
 * 200. 岛屿数量
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/10 17:51
 */
public class _200_NumberOfIslands {
    /**
     * 脑筋急转弯法
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int result = 0;
        if (grid == null || grid.length < 1) return result;
        int colNum = grid[0].length;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < colNum; col++) {
                if (grid[row][col] == '1') {
                    result++;
                    resetAround(grid, row, col);
                }
            }
        }
        return result;
    }
    private void resetAround(char[][] grid, int row, int col) {
        if(row < 0 || col < 0 ||row >= grid.length || col >= grid[0].length || grid[row][col] == '0'){
            return;
        }
        grid[row][col] = 0;
        resetAround(grid, row + 1, col);
        resetAround(grid, row - 1, col);
        resetAround(grid, row, col + 1);
        resetAround(grid, row, col - 1);
    }

    //           x-1,y
    //  x,y-1    x,y      x,y+1
    //           x+1,y
    // 方向数组，它表示了相对于当前位置的 4 个方向的横、纵坐标的偏移量，这是一个常见的技巧
    private static final int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    // 标记数组，标记了 grid 的坐标对应的格子是否被访问过
    private boolean[][] marked;
    // 入参grid
    private char[][] grid;
    // grid 的行数
    private int rows;
    // grid 的列数
    private int cols;

    /**
     * 深度优先遍历
     * @param grid
     * @return
     */
    public int numIslandsV2(char[][] grid) {
        rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        cols = grid[0].length;
        this.grid = grid;
        marked = new boolean[rows][cols];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果是岛屿中的一个点，并且没有被访问过
                // 就进行深度优先遍历
                if (!marked[i][j] && grid[i][j] == '1') {
                    count++;
                    dfs(i, j);
                }
            }
        }
        return count;
    }
    // 从坐标为 (i,j) 的点开始进行深度优先遍历
    private void dfs(int i, int j) {
        marked[i][j] = true;
        // 得到 4 个方向的坐标
        for (int k = 0; k < 4; k++) {
            int newX = i + directions[k][0];
            int newY = j + directions[k][1];
            // 如果不越界、没有被访问过、并且还要是陆地
            if (inArea(newX, newY) && grid[newX][newY] == '1' && !marked[newX][newY]) {
                dfs(newX, newY);
            }
        }
    }

    // 封装成 inArea 方法语义更清晰
    private boolean inArea(int x, int y) {
        // 等于号不要忘了
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    /**
     * 广度优先遍历，重用了一些全局变量
     * @param grid
     * @return
     */
    public int numIslandsV3(char[][] grid) {
        rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        cols = grid[0].length;
//        this.grid = grid;
        marked = new boolean[rows][cols];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果是岛屿中的一个点，并且没有被访问过
                // 从坐标为 (i,j) 的点开始进行广度优先遍历
                if (!marked[i][j] && grid[i][j] == '1') {
                    count++;
                    LinkedList<Integer> queue = new LinkedList<>();
                    // 小技巧：把坐标转换为一个数字，否则，得用一个数组存
                    queue.addLast(i * cols + j);
                    // 注意：这里要标记上已经访问过
                    marked[i][j] = true;
                    while (!queue.isEmpty()) {
                        int cur = queue.removeFirst();
                        int thisRow = cur / cols;
                        int thisCol = cur % cols;
                        // 得到 4 个方向的坐标
                        for (int k = 0; k < 4; k++) {
                            int newRow = thisRow + directions[k][0];
                            int newCol = thisCol + directions[k][1];
                            // 如果不越界、没有被访问过、并且还要是陆地，就继续放入队列，放入队列的同时，要记得标记已经访问过
                            if (inArea(newRow, newCol) && grid[newRow][newCol] == '1' && !marked[newRow][newCol]) {
                                queue.addLast(newRow * cols + newCol);
                                // 【特别注意】在放入队列以后，要马上标记成已经访问过，语义也是十分清楚的：反正只要进入了队列，你迟早都会遍历到它
                                // 而不是在出队列的时候再标记
                                // 【特别注意】如果是出队列的时候再标记，会造成很多重复的结点进入队列，造成重复的操作，这句话如果你没有写对地方，代码会严重超时的
                                marked[newRow][newCol] = true;
                            }
                        }
                    }
                }
            }

        }
        return count;
    }

    /** 并查集方式 */
    public int numIslandsV4(char[][] grid){
        if (grid == null || grid.length == 0) return 0;
        int totalRow = grid.length;
        int totalCol = grid[0].length;
        UnionFind uf = new UnionFind(totalCol * totalRow + 1);
        // 虚假节点，用于和所有水域相连
        int fakeNode = totalCol * totalRow;
        for (int i = 0; i < totalRow; i++) {
            for (int j = 0; j < totalCol; j++) {
                // 陆地，则向右、下两个方向扩散
                int crtCoor = transferCoordinate(i, j, totalCol);
                if(grid[i][j] == '1'){
                    if(i+1 < totalRow && grid[i+1][j] == '1'){
                        uf.union(crtCoor,transferCoordinate(i+1,j,totalCol));
                    }
                    if(j+1 < totalCol && grid[i][j+1] == '1'){
                        uf.union(crtCoor,transferCoordinate(i,j + 1,totalCol));
                    }
                }else{
                    // 水域和fakeNode连接
                    uf.union(fakeNode,crtCoor);
                }
            }
        }
        return uf.getIndependentArea();
    }
    private int transferCoordinate(int row, int col, int totalCol) {
        return row * totalCol + col;
    }
}
