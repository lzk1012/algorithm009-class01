package com.xx.leetcode.week03.homework;

import java.util.HashMap;
import java.util.Map;

/**
 * 169. 多数元素
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/3 14:40
 */
public class _169_MajorityElement {
    public static void main(String[] args) {
        new _169_MajorityElement().majorityElement(new int[]{3,2,3});
    }
    // 哈希表方法
    public int majorityElement(int[] nums) {
        int limit = nums.length/2;
        Map<Integer,Integer> map = new HashMap<>();
        for(int n:nums){
            Integer curTimes = map.getOrDefault(n, 0) + 1;
            if(curTimes > limit){
                return n;
            }else{
                map.put(n,curTimes);
            }
        }
        return 0;
    }
}
