package com.xx.local;

/**
 * TODO 类描述信息
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/5/24 19:11
 */
public class RotateArray_189 {
    /**
     * 189. 旋转数组
     * @param nums 数组
     * @param k 旋转次数
     */
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        // 三个元素的数组，移动4次和移动1次的结果是一样的
        k = k % len;
        int changedNum = 0;
        for(int i = 0;changedNum < len;i++){
            int current = i;
            int currentValue = nums[current];
            do{
                int next = (current + k) % len;
                int nextValue = nums[next];
                nums[next] = currentValue;
                current = next;
                currentValue = nextValue;
                changedNum++;
            }while(current != i);
        }
    }
}
