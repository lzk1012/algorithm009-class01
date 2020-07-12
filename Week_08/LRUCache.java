package com.xx.leetcode.support;

import java.util.HashMap;

/**
 * 146. LRU缓存机制
 * 还可以继承LinkedHashMap。
 *   主要就是两个地方，一是accessOrder设置为true，二是重写removeEldestEntry，以保证LRU结构中的数据大小在capacity范围内
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/10 14:05
 */
public class LRUCache {
    // key -> Node(key, val)
    private HashMap<Integer, Node> map;
    // Node(k1, v1) <-> Node(k2, v2)...
    private TwoWayLinkedList cache;
    // 最大容量
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new TwoWayLinkedList();
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        // 更新双向链表
        Node node = map.get(key);
        put(node.key,node.val);
        return node.val;
    }

    public void put(int key, int val) {
        // 先把新节点 x 做出来
        Node x = new Node(key, val);

        if (map.containsKey(key)) {
            // 删除旧的节点，新的插到头部
            cache.remove(map.get(key));
        } else if (cap == cache.size()) {
            // 删除链表最后一个数据
            Node last = cache.removeLast();
            map.remove(last.key);
        }
        cache.addFirst(x);
        // 更新 map 中对应的数据
        map.put(key, x);
    }

}

class TwoWayLinkedList{
    private int size;
    public Node head,tail;

    public TwoWayLinkedList() {
        this.size = 0;
        this.head = new Node(0,0);
        this.tail = new Node(0,0);
        this.head.next = tail;
        this.tail.prev = head;
    }

    // 在链表头部添加节点 x
    public void addFirst(Node x) {
        size++;
        x.next = head.next;
        x.prev = head;
        head.next.prev = x;
        head.next = x;
    }
    // 删除链表中的 x 节点（x 一定存在）
    public void remove(Node x) {
        size--;
        x.prev.next = x.next;
        x.next.prev = x.prev;
    }
    // 删除链表中最后一个节点，并返回该节点
    public Node removeLast() {
        if (tail.prev == head)
            return null;
        Node last = tail.prev;
        remove(last);
        return last;
    }

    // 返回链表长度
    public int size() { return size; }
}

class Node{
    public int key,val;
    public Node next,prev;
    public Node(int key,int val){
        this.key = key;
        this.val = val;
    }
}