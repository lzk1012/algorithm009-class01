package com.xx.leetcode.week07.homework;

import com.xx.leetcode.support.UnionFind;

/**
 * 547. 朋友圈
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/30 14:37
 */
public class _547_FriendCircles {
    public int findCircleNum(int[][] M) {
        int size = M.length;
        UnionFind uf = new UnionFind(size);
        for (int i = 0; i < size; i++) {
            // 对称矩阵，因此遍历范围减少。此外左上对角线的值全是1,UnionFind构造函数中默认就是这种情况，也不用处理
            for (int j = 0; j < i; j++) {
                if(M[i][j] == 1){
                    uf.union(i,j);
                }
            }
        }
        return uf.getIndependentArea();
    }
}

