package cn.offer2020.pbj.demo.leetcode.a_recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo17
 * @Author: pbj
 * @Date: 2020/4/9 15:01
 * @Description: TODO 17. 电话号码的字母组合
 */
public class Demo17 {

    private static final String[] keys = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if(digits==null||digits.length()==0){
            return ans;
        }
        doCombinations(new StringBuilder(),ans,digits);
        return ans;
    }
    private void doCombinations(StringBuilder sb,List<String> ans,String digits){
        if(sb.length()==digits.length()){
            ans.add(sb.toString());
            return;
        }
        int curDigits = digits.charAt(sb.length())-'0';
        String letters = keys[curDigits];
        for(char c : letters.toCharArray()){
            sb.append(c);
            doCombinations(sb,ans,digits);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
