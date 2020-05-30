package com.xx.leetcode.week02.homework;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 347. 前 K 个高频元素
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/5/28 20:23
 */
public class _347_TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];

        Map<Integer,Integer> map = new HashMap<>();
        for(int n : nums){
            map.put(n,map.getOrDefault(n,0)+1);
        }
        Queue<Map.Entry<Integer,Integer>> heap = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());

        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            heap.add(entry);
        }

        for(int i = 0;i<k;i++){
            result[i] = heap.poll().getKey();
        }

        return result;
    }
}
class ElementFrequent{
    int value;
    int frequent;
}