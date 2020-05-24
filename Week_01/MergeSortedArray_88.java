package com.xx.local;

/**
 * TODO 类描述信息
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/5/24 19:13
 */
public class MergeSortedArray_88 {

    /**
     * 88. 合并两个有序数组
     * @param nums1 有序数组1
     * @param m 数组1中要合并的前m个元素
     * @param nums2 有序数组2
     * @param n 数组2中要合并的前n个元素
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int [] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);
        int indexM = 0,indexN = 0,index = 0;

        while ((indexM < m) && (indexN < n)){
            if(nums1_copy[indexM] > nums2[indexN]){
                nums1[index] = nums2[indexN];
                indexN++;
            }else{
                nums1[index] = nums1_copy[indexM];
                indexM++;
            }
            index++;
//            nums1[index++] = nums1_copy[indexM] > nums2[indexN] ? nums2[indexN++] : nums1_copy[indexM++];
        }
        if(indexM >= m){
            while(indexN < n){
                nums1[index++] = nums2[indexN++];
            }
        }else{
            while(indexM < m){
                nums1[index++] = nums1_copy[indexM++];
            }
        }
    }
}
