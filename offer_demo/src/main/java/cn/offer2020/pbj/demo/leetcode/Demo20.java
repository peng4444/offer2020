package cn.offer2020.pbj.demo.leetcode;

import java.util.HashMap;
import java.util.Stack;

/**
 * @ClassName: Demo20
 * @Author: pbj
 * @Date: 2019/12/12 10:01
 * @Description: TODO 有效的括号
 */
public class Demo20 {

    private HashMap<Character,Character> map;
    public Demo20(){
        this.map = new HashMap<>();
        this.map.put(')', '(');
        this.map.put('}', '{');
        this.map.put(']', '[');
    }

    /* *
     * 功能描述: 官方解答 全部完成迭代才返回结果
     * 时间复杂度空间复杂度O(n)
     * @param: [s]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/12/12 10:08
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (this.map.containsKey(c)) {
                char topElement = stack.empty() ? '#' : stack.pop();
                if (topElement != this.map.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }


    public boolean isValid1(String s) {
        HashMap<Character,Character> map2 = new HashMap<>();
        map2.put(')','(');
        map2.put('}','{');
        map2.put(']','[');
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            if(map2.containsKey(c)){
                char topElement = stack.empty()?'#':stack.pop();
                if(topElement!=map.get(c)){
                    return false;
                }
            }else{
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
    /* *
     * 功能描述: 取巧法
     * 时间复杂度大
     * @param: [s]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/12/12 10:21
     */
    public boolean isValid2(String s) {
        int length;
        do{
            length = s.length();
            s = s.replace("()","").replace("{}","").replace("[]","");
        }while(length!=s.length());
        return s.length()==0;
    }
}
