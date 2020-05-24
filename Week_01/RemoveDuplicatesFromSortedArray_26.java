package com.xx.local;

/**
 * 26. 删除排序数组中的重复项
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/5/24 19:10
 */
public class RemoveDuplicatesFromSortedArray_26 {

    /**
     * 26. 删除排序数组中的重复项
     * @param nums 排序数组
     * @return 数组新长度
     */
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int i = 0,j = 1;
        while(j < nums.length){
            if(nums[j] != nums[i]){
                i++;
                nums[i] = nums[j];
            }
            j++;
        }
        return ++i;
    }
}
