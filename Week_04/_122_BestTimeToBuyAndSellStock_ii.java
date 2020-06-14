package com.xx.leetcode.week04.homework;

/**
 * 121. 买卖股票的最佳时机
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/10 11:44
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 122. 买卖股票的最佳时机 II
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/10 11:44
 */
public class _122_BestTimeToBuyAndSellStock_ii {
    public static void main(String[] args) {
        new _122_BestTimeToBuyAndSellStock_ii().maxProfit(new int[]{7,1,5,3,6,4});
    }
    public int maxProfit(int[] prices) {
        int result = 0;
        if(prices == null || prices.length < 2){
            return result;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i : prices){
            if(stack.isEmpty()){
                stack.addLast(i);
                continue;
            }
            Integer peekLast = stack.peekLast();
            if(i == peekLast){
                continue;
            }else if(i > peekLast){
                if(stack.size() == 2){
                    stack.pollLast();
                }
                stack.addLast(i);
            }else if(i < peekLast){
                result += calcProfit(stack);
                stack.addLast(i);
            }
        }
        result += calcProfit(stack);
        return result;
    }

    private int calcProfit(Deque<Integer> stack){
        int result = 0;
        if(stack.size() == 2){
            int sellValue = stack.pollLast();
            int buyValue = stack.pollLast();
            result = sellValue - buyValue;
        }else{
            stack.clear();
        }
        return result;
    }
}