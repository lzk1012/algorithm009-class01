package com.xx.local;

/**
 * TODO 类描述信息
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/5/24 19:12
 */
public class MergeTwoSortedLists_21 {
    /**
     * 21. 合并两个有序链表
     * @param l1 给定有序链表1
     * @param l2 给定有序链表2
     * @return 合并有序链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        ListNode node = result;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                node.next = l1;
                l1 = l1.next;
            }else{
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        node.next = l1 == null ? l2:l1;
        return result.next;
    }
}
