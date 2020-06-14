package com.xx.leetcode.week04.homework;

/**
 * 153. 寻找旋转排序数组中的最小值
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/14 13:38
 */
public class _153_FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            int mid = (right + left) >>> 1;
            // 如果中间元素小于最右元素，则[mid,right]升序，最小值在[left,mid]之间
            if(nums[mid] < nums[right]){
                right = mid;
            }else{
                // 如果中间元素大于最左元素，则[left,mid]升序，此时分两种情况：
                    // 1.整个[left,right]升序，最小值是left,但是这种情况在第一次if中判断了
                    // 2.仅[left,mid]升序，最小值在mid后面
                left = mid+1;
            }
        }

        return nums[left];
    }
}
