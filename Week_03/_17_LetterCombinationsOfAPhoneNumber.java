package com.xx.leetcode.week03.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/3 15:47
 */
public class _17_LetterCombinationsOfAPhoneNumber {
    List<String> result = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if("".equals(digits)){
            return result;
        }
        Map<Character,String> map = new HashMap<Character, String>(){{
            put('2',"abc");
            put('3',"def");
            put('4',"ghi");
            put('5',"jkl");
            put('6',"mno");
            put('7',"pqrs");
            put('8',"tuv");
            put('9',"wxzy");
        }};
        doLetterCombinations(digits,map,0,new StringBuilder());
        return result;
    }

    private void doLetterCombinations(String digits,Map<Character,String> map,int level,StringBuilder currentStr){
        if(level == digits.length()){
            result.add(new String(currentStr));
            return;
        }
//        for (int i = level; i < digits.length(); i++) {
            String str = map.get(digits.charAt(level));
            for (char ch : str.toCharArray()) {
                currentStr.append(ch);
                doLetterCombinations(digits, map, level + 1, currentStr);
                currentStr.deleteCharAt(currentStr.length() - 1);
            }
//        }
    }
}
