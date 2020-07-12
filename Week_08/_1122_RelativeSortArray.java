package com.xx.leetcode.week08.daily;

/**
 * 1122. 数组的相对排序
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/7/11 13:23
 */
public class _1122_RelativeSortArray {
    public static void main(String[] args) {
        new _1122_RelativeSortArray().relativeSortArray(new int[]{28,6,22,8,44,17},new int[]{22,28,8,6});
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int arr1Min = Integer.MAX_VALUE;
        int arr1Max = 0;
        for(int value : arr1){
            arr1Min = Math.min(arr1Min,value);
            arr1Max = Math.max(arr1Max,value);
        }
        int shift = arr1Max - arr1Min;
        int[] cache = new int[shift+1];

        for(int value : arr1){
            cache[value - arr1Min]++;
        }
        int[] result = new int[arr1.length];
        int resultIndex = 0;
        for(int value : arr2){
            while(cache[value - arr1Min] != 0){
                result[resultIndex++] = value;
                cache[value - arr1Min]--;
            }
        }
        for(int i = 0;i<=shift;i++){
            int value = cache[i];
            while(value != 0){
                result[resultIndex++] = i+arr1Min;
                value--;
            }
        }
        return result;
    }
}
