package com.xx.leetcode.week04.homework;

import java.util.Arrays;

/**
 * 455. 分发饼干
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/12 14:52
 */
public class _455_AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        int result = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int gIndex = 0;
        int sIndex = 0;
        while(sIndex < s.length && gIndex < g.length){
            if(s[sIndex] >= g[gIndex]){
                sIndex++;
                gIndex++;
                result++;
            }else{
                sIndex++;
            }
        }
        return  result;
    }
}
