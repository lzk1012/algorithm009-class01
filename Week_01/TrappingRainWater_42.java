package com.xx.local;

/**
 * TODO 类描述信息
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/5/24 19:16
 */
public class TrappingRainWater_42 {
    /**
     * 42. 接雨水
     * @param height 高度数组
     * @return 雨水容量
     */
    public int trap(int[] height) {
        if(height == null || height.length <= 2){
            return 0;
        }
        int result = 0;
        // 黑色区域面积
        int maxHeightIndex = 0;
        int blackArea = height[maxHeightIndex];
        // 从左到右循环，找到最高列，同时，求出左边能接的雨水
        for(int i = 1;i<height.length;i++){
            if(height[i] >= height[maxHeightIndex]){
                result += height[maxHeightIndex] * (i - maxHeightIndex) - blackArea;
                maxHeightIndex = i;
                blackArea = height[i];
            }else{
                blackArea += height[i];
            }
        }
        int j = height.length - 1;
        blackArea = height[j];
        // 从右到最高列循环，求出右边能接的雨水
        for(int i = height.length - 2;i>=maxHeightIndex;i--){
            if(height[i] >= height[j]){
                result += height[j] * (j - i) - blackArea;
                j = i;
                blackArea = height[i];
            }else{
                blackArea += height[i];
            }
        }

        return result;
    }
}
