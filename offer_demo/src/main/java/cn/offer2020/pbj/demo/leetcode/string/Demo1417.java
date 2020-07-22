package cn.offer2020.pbj.demo.leetcode.string;

import java.util.Stack;

/**
 * @pClassName: Demo1417
 * @author: pengbingjiang
 * @create: 2020/7/22 11:37
 * @description: TODO 1417. 重新格式化字符串
 */
public class Demo1417 {
    public String reformat(String s) {
        if(s==null||s.length()==0) return "";
        char[] cs = s.toCharArray();
        StringBuilder s1 = new StringBuilder();
        StringBuilder sa = new StringBuilder();
        for(int i = 0;i<cs.length;i++){
            if(cs[i]>='0'&&cs[i]<='9'){
                s1.append(cs[i]);
            }else{
                sa.append(cs[i]);
            }
        }
        if(Math.abs(s1.length()-sa.length())>1) return "";
        StringBuilder sb = new StringBuilder();
        int len = cs.length;
        if(s1.length()>sa.length()){
            int i = 0;
            while(i<(len-1)/2){
                sb.append(s1.charAt(i));
                sb.append(sa.charAt(i));
                i++;
            }
            sb.append(s1.charAt(s1.length()-1));
        }else if(s1.length()<sa.length()){
            int i = 0;
            while(i<(len-1)/2){
                sb.append(sa.charAt(i));
                sb.append(s1.charAt(i));
                i++;
            }
            sb.append(sa.charAt(sa.length()-1));
        }else{
            int i = 0;
            while(i<len/2){
                sb.append(sa.charAt(i));
                sb.append(s1.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

    //1.先把字母和数字分开，分别放入stack中
    //2.要拼接成功，必须两个相减绝对值小于1，否则返回"";
    //3.拼接过程中是先放字母还是数字，取决于判断两个stack大小（谁大谁先放，我做了判断，让大的保持先遍历）
    public String reformat1(String s) {
        Stack<Character> stackAZ = new Stack();
        Stack<Character> stack09 = new Stack();
        Stack<Character> temp = new Stack();
        String result="";
        for(char c : s.toCharArray()){
            if(c >= '0' && c <= '9'){
                stack09.add(c);
            }else{
                stackAZ.add(c);
            }
        }
        if (Math.abs(stack09.size() - stackAZ.size()) > 1) {
            return result;
        }
        if (stack09.size()> stackAZ.size()) {
            temp = stack09;
            stack09 = stackAZ;
            stackAZ = temp;
        }
        for (stackAZ.size();  stackAZ.size() > 0; ) {
            result= result + String.valueOf(stackAZ.pop());
            if(stack09.empty()) {
                continue;
            } else {
                result = result + String.valueOf(stack09.pop());
            }
        }
        return result;
    }

}
