package cn.offer2020.pbj.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo241
 * @Author: pbj
 * @Date: 2020/4/8 15:10
 * @Description: TODO
 */
public class Demo241 {

    //分治算法
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ans = new ArrayList<>();
        for(int i = 0;i<input.length();i++){
            char c = input.charAt(i);
            if(c=='+'||c=='-'||c=='*'){
                List<Integer> left = diffWaysToCompute(input.substring(0,i));
                List<Integer> right = diffWaysToCompute(input.substring(i+1));
                for(int l:left){
                    for(int r:right){
                        switch(c){
                            case '+':
                                ans.add(l+r);
                                break;
                            case '-':
                                ans.add(l-r);
                                break;
                            case '*':
                                ans.add(l*r);
                                break;
                        }
                    }
                }
            }
        }
        if(ans.size()==0){
            ans.add(Integer.valueOf(input));
        }
        return ans;
    }

    //递归
    public List<Integer> diffWaysToCompute1(String input) {
        if (input.length() == 0) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        int num = 0;
        //考虑是全数字的情况
        int index = 0;
        while (index < input.length() && !isOperation(input.charAt(index))) {
            num = num * 10 + input.charAt(index) - '0';
            index++;
        }
        //将全数字的情况直接返回
        if (index == input.length()) {
            result.add(num);
            return result;
        }

        for (int i = 0; i < input.length(); i++) {
            //通过运算符将字符串分成两部分
            if (isOperation(input.charAt(i))) {
                List<Integer> result1 = diffWaysToCompute1(input.substring(0, i));
                List<Integer> result2 = diffWaysToCompute1(input.substring(i + 1));
                //将两个结果依次运算
                for (int j = 0; j < result1.size(); j++) {
                    for (int k = 0; k < result2.size(); k++) {
                        char op = input.charAt(i);
                        result.add(caculate(result1.get(j), op, result2.get(k)));
                    }
                }
            }
        }
        return result;
    }

    private int caculate(int num1, char c, int num2) {
        switch (c) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
        }
        return -1;
    }

    private boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*';
    }
}
