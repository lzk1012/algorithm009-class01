package com.xx.local;

/**
 * TODO 类描述信息
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/5/24 19:14
 */
public class MoveZeroes_283 {
    /**
     * 283. 移动零
     * @param nums 原始数组
     */
    public void moveZeroes(int[] nums) {
        if(nums==null) {
            return;
        }
        // j指向要替换的0元素
        int j = 0;
        // i指向非零元素
        // 起始情况二者都是0下标，此时如果此元素为0，j不变，i++；如果不为零，二者同加
        for(int i=0;i<nums.length;i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if(nums[i] != 0){
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }
    }
}
