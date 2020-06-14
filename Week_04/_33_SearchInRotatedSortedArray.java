package com.xx.leetcode.week04.homework;

/**
 * 33. 搜索旋转排序数组
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/13 11:31
 */
public class _33_SearchInRotatedSortedArray {
    public static void main(String[] args) {
        new _33_SearchInRotatedSortedArray().searchV2(new int[]{1,3},1);
    }
    public int searchV2(int[] nums, int target){
        if(nums.length < 1){
            return -1;
        }
        int left = 0;
        int right = nums.length -1;
        while(left < right){
            int mid = (right + left) >>> 1;
            if(nums[mid] < nums[right]){
                if (nums[mid] <= target && target <= nums[right]) {
                    // 下一轮搜索区间是 [mid, right]
                    left = mid;
                } else {
                    // 只要上面对了，这个区间是上面区间的反面区间，下一轮搜索区间是 [left, mid - 1]
                    right = mid - 1;
                }
            }else{
                if (nums[left] <= target && target <= nums[mid]) {
                    // 下一轮搜索区间是 [left, mid]
                    right = mid;
                } else {
                    // 下一轮搜索区间是 [mid + 1, right]
                    left = mid + 1;
                }
            }
        }
        // 有可能区间内不存在目标元素，因此还需做一次判断
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }

    public int searchV1(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }

        int left = 0;
        int right = len - 1;
        while (left < right) {

            int mid = left + (right - left + 1) / 2;

            if (nums[mid] < nums[right]) {

                // 使用上取整的中间数，必须在上面的 mid 表达式的括号里 + 1
                if (nums[mid] <= target && target <= nums[right]) {
                    // 下一轮搜索区间是 [mid, right]
                    left = mid;
                } else {
                    // 只要上面对了，这个区间是上面区间的反面区间，下一轮搜索区间是 [left, mid - 1]
                    right = mid - 1;
                }

            } else {

                // [left, mid] 有序，但是为了和上一个 if 有同样的收缩行为，
                // 我们故意只认为 [left, mid - 1] 有序
                // 当区间只有 2 个元素的时候 int mid = (left + right + 1) >>> 1; 一定会取到右边
                // 此时 mid - 1 不会越界，就是这么刚刚好

                if (nums[left] <= target && target <= nums[mid - 1]) {
                    // 下一轮搜索区间是 [left, mid - 1]
                    right = mid - 1;
                } else {
                    // 下一轮搜索区间是 [mid, right]
                    left = mid;
                }
            }
        }

        // 有可能区间内不存在目标元素，因此还需做一次判断
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            // 此时代表[mid,right]升序
            if (nums[mid] <= nums[right]) {
                // 如果此时target在[mid,right]中的条件为：
                if(nums[right] == target){
                    return right;
                }else if(nums[mid] == target){
                    return mid;
                }else if (nums[mid] < target && target < nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else if (nums[mid] >= nums[left]) {// [left,mid]升序
                // 如果此时target在[left,mid]中的条件为：
                if(nums[left] == target){
                    return left;
                }else if(nums[mid] == target){
                    return mid;
                }else if (nums[left] < target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        if(left == nums.length){
            return -1;
        }
        return nums[left] == target?left:-1;
    }
}
