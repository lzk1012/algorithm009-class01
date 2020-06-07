package com.xx.leetcode.week03.homework;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/1 20:56
 */
public class _105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    private Map<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length == 0){
            return null;
        }
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return doBuildTree(preorder,inorder,0,n-1,0,n-1);
    }

    private TreeNode doBuildTree(int[] preorder, int[] inorder,int preorderLeft,int preorderRight,int inorderLeft,int inorderRight) {
        if (preorderLeft > preorderRight) {
            return null;
        }
        int preorder_root = preorderLeft;
        int inorder_root = indexMap.get(preorder[preorder_root]);
        TreeNode root = new TreeNode(preorder[preorder_root]);

        int leftTreeSize = inorder_root - inorderLeft;

        root.left = doBuildTree(preorder,inorder,preorderLeft + 1,preorderLeft+leftTreeSize,inorderLeft,inorder_root -1);
        root.right = doBuildTree(preorder,inorder,preorderLeft+leftTreeSize + 1,preorderRight,inorder_root + 1, inorderRight);
        return root;

    }
}
