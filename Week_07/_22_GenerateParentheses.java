package com.xx.leetcode.week03.homework;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/1 11:51
 */
public class _22_GenerateParentheses {
    public static void main(String[] args) {
        new _22_GenerateParentheses().generateParenthesis(3);
    }

    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>(n * 2);
        doGenerateParenthesis(0,0,2*n,"",result);
        return result;
    }

    private void doGenerateParenthesis(int leftNum,int rightNum,int totalLen,String generatedStr,List<String> result){
        if(generatedStr.length() >= totalLen){
            result.add(generatedStr);
            return;
        }
        if(leftNum < totalLen/2){
            doGenerateParenthesis(leftNum + 1,rightNum,totalLen,generatedStr+"(", result);
        }
        if(leftNum > rightNum){
            doGenerateParenthesis(leftNum,rightNum + 1,totalLen,generatedStr+")", result);
        }
    }
}

