package com.xx.leetcode.week02.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 429. N叉树的层序遍历
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/5/28 11:04
 */
public class _429_NAryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node3 = new Node(3, Arrays.asList(node5,node6));
        Node node2 = new Node(2);
        Node node4 = new Node(4);
        Node node1 = new Node(1,Arrays.asList(node3,node2,node4));
        new _429_NAryTreeLevelOrderTraversal().levelOrder(node1);

    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<Node> previousLayer = Arrays.asList(root);

        while (!previousLayer.isEmpty()) {
            List<Node> currentLayer = new ArrayList<>();
            List<Integer> previousVals = new ArrayList<>();
            for (Node node : previousLayer) {
                previousVals.add(node.val);
                currentLayer.addAll(node.children);
            }
            result.add(previousVals);
            previousLayer = currentLayer;
        }

        return result;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};