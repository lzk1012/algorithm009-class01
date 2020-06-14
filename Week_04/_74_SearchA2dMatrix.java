package com.xx.leetcode.week04.homework;

/**
 * 74. 搜索二维矩阵
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/14 12:12
 */
public class _74_SearchA2dMatrix {
    public static void main(String[] args) {
        new _74_SearchA2dMatrix().searchMatrix(new int[][]{{1,   3,  5,  7},{10, 11, 16, 20},{23, 30, 34, 50}},13);
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        int matrixLen = matrix.length;
        if (matrixLen < 1) {
            return false;
        }
        int len = matrix[0].length;
        if (len < 1) {
            return false;
        }

        int start = 0;
        int end = matrixLen - 1;
        while(start <= end){
            int matrixMid = (start + end) >>> 1;
            int[] arr = matrix[matrixMid];
            // 不在当前数组的范围中
            if(arr[0] > target){
                end = matrixMid - 1;
                continue;
            }else if(arr[len - 1] < target){
                start = matrixMid + 1;
                continue;
            }
            // 在当前数组的范围中
            int left = 0;
            int right = len - 1;
            while(left <= right){
                int mid = (right + left) >>> 1;
                if(arr[mid] == target){
                    return true;
                }else if(arr[mid] > target){
                    right = mid - 1;
                }else if(arr[mid] < target){
                    left = mid + 1;
                }
            }
            // 在当前数组范围，但是找不到对应的值，返回false
            return false;
        }

        return false;
    }
}
