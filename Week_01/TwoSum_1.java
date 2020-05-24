package com.xx.local;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO 类描述信息
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/5/24 19:13
 */
public class TwoSum_1 {

    /**
     * 1. 两数之和
     * @param nums 给定数值
     * @param target 目标值
     * @return 两个加数
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++){
            // 差值
            int i1 = target - nums[i];
            if(map.containsKey(i1)){
                return new int[]{map.get(i1),i};
            }
            map.put(nums[i],i);
        }
        return null;
    }
}
