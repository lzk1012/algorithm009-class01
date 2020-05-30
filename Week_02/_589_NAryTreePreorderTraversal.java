package com.xx.leetcode.week02.homework;

import java.util.*;

/**
 * 589. N叉树的前序遍历
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/5/28 11:05
 */
public class _589_NAryTreePreorderTraversal {
    public static void main(String[] args) {
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node3 = new Node(3, Arrays.asList(node5,node6));
        Node node2 = new Node(2);
        Node node4 = new Node(4);
        Node node1 = new Node(1,Arrays.asList(node3,node2,node4));
        new _589_NAryTreePreorderTraversal().preorder(node1);
    }

    public List<Integer> preorder(Node root) {
        // 递归法start
//        List<Integer> result = new ArrayList<>();
//        doPreorder(root,result);
        // 递归法end

        // 迭代法
        List<Integer> result = doPreorder(root);
        return result;
    }

    /**
     * 递归实现
     * @param root
     * @return
     */
    private List<Integer> doPreorder(Node root) {
        // 实现方式和二叉树的前序一致：子节点从右到左入栈即可
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        LinkedList<Node> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            result.add(node.val);
            if(node.children != null){
                Collections.reverse(node.children);
                for(Node n : node.children){
                    stack.push(n);
                }
            }
        }
        return result;
    }

    /**
     * 递归法
     * @param root
     * @param result
     */
    private void doPreorder(Node root, List<Integer> result) {
        if(root == null){
            return;
        }
        result.add(root.val);
        for(Node node : root.children){
            doPreorder(node,result);
        }
    }
}
