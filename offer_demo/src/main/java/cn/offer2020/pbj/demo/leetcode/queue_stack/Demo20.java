package cn.offer2020.pbj.demo.leetcode.queue_stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * @ClassName: Demo20
 * @Author: pbj
 * @Date: 2019/12/12 10:01
 * @Description: TODO 20.有效的括号
 */
public class Demo20 {
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
                if(topElement!=map2.get(c)){
                    return false;
                }
            }else{
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    //建立一个新的栈，然后遍历字符串的字符，进行比较
    public boolean isValid3(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if(c=='(') stack.push(')');
            else if(c=='[') stack.push(']');
            else if(c=='{') stack.push('}');
            else if(stack.isEmpty()||c!=stack.pop()) return false;
        }
        return stack.isEmpty();
    }
    /* *
     * 功能描述: 取巧法 使用字符串替换和递归的方式
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
