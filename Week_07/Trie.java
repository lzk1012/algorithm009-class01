package com.xx.leetcode.support;

/**
 * 前缀树、字典树
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/1 11:26
 */
public class Trie {

    // 只针对26个小写英文字母的情况
    public Trie[] tries = new Trie[26];
    // 叶子节点上存储插入的值
    public String leafValue;
    // 判断是否叶子节点
    public boolean isEnd;

    public Trie(){}

    public void insert(String word) {
        Trie node = this;
        for(char ch : word.toCharArray()){
            int index = ch - 'a';
            if(node.tries[index] == null){
                node.tries[index] = new Trie();
            }
            node = node.tries[index];
        }
        node.isEnd = true;
        node.leafValue = word;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchEnd(word);
        return node != null && node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchEnd(prefix) != null;
    }

    /** search a prefix or whole key in trie and returns the node where search ends */
    private Trie searchEnd(String key){
        Trie node = this;
        for(char ch : key.toCharArray()){
            int index = ch - 'a';
            node = node.tries[index];
            if(node == null){
                return null;
            }
        }
        return node;
    }
}
