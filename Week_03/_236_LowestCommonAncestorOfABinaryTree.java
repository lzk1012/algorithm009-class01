package com.xx.leetcode.week03.homework;

import java.util.HashMap;
import java.util.Map;

/**
 * 236. 二叉树的最近公共祖先
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/1 20:15
 */
public class _236_LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }
        if(root.val == p.val || root.val == q.val){
            return root;
        }

        TreeNode nodeLeft = lowestCommonAncestor(root.left,p,q);
        TreeNode nodeRight = lowestCommonAncestor(root.right,p,q);

        if(nodeLeft == null){
            return nodeRight;
        }else if(nodeRight == null){
            return nodeLeft;
        }else if(nodeLeft.val != nodeRight.val){
            return root;
        }
        return nodeLeft;
    }
}
