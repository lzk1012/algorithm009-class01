package com.xx.leetcode.week09.homework;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. 同构字符串
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/19 11:45
 */
public class _205_IsomorphicStrings {
    public static void main(String[] args) {
        new _205_IsomorphicStrings().isIsomorphic("ab","aa");
    }
    public boolean isIsomorphic(String s, String t) {
        Map<Character,Character> map = new HashMap<>();
        for(int i = 0;i<s.length();i++){
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (!map.containsKey(sc)) {
                if (!map.containsValue(tc)){
                    map.put(sc, tc);
                }else{
                    return false;
                }
            }else if (map.get(sc)!=tc) return false;

        }
        return true;
    }
}
