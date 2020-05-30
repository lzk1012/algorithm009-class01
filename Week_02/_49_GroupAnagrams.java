package com.xx.leetcode.week02.homework;

import java.util.*;

/**
 * TODO 类描述信息
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/5/26 20:15
 */
public class _49_GroupAnagrams {
    public static void main(String[] args) {
        new _49_GroupAnagrams().groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();

        for(String str : strs){
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = String.valueOf(charArray);
            if(map.containsKey(key)){
                map.get(key).add(str);
            }else{
                map.put(key,new ArrayList<String>(){{
                    add(str);
                }});
            }
        }

        return new ArrayList<>(map.values());
    }
}
