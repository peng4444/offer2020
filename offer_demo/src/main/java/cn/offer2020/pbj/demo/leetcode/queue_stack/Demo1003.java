package cn.offer2020.pbj.demo.leetcode.queue_stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @pClassName: Demo1003
 * @author: pengbingjiang
 * @create: 2020/7/11 17:52
 * @description: TODO 1003.检查替换后的词是否有效
 */
public class Demo1003 {
    //stack
    public static boolean isValid_stack2(String S) {
        Stack<Character> stack = new Stack<>();
        for (char cs : S.toCharArray()) {
            if (cs == 'a' || cs == 'b') {
                stack.push(cs);
            } else if (cs == 'c') {
                // 当前字符是c,如果前面不是"ab"串,肯定是false
                if (stack.size() < 2 || stack.pop() != 'b' || stack.pop() != 'a') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    //数组stack
    public static boolean isValid3(String S) {
        char[] s = S.toCharArray();
        char[] stack = new char[s.length];
        int index = 0;
        for (char cs : s) {
            if (cs != 'c') {
                stack[index++] = cs;
            } else {
                // 当前字符是c,如果前面不是"ab"串,肯定是false
                if (index < 2 || stack[--index ] != 'b'|| stack[--index] != 'a' ) {
                    return false;
                }
            }
        }
        return index == 0;
    }

    //:因为有效字符串"abc"的结尾是字符'c',
    //所以,每遍历到一个字符'c'时,则先判断当前栈中包含的字符是否可以构成"ab",
    //若当前栈中靠近栈顶的前两个字符可以构成"ab"则继续,
    //若当前栈为空或者当前栈中靠近栈顶的前两个字符构不成"ab",则直接返回false;
    //在遍历完字符串S后,再判断栈是否为空,若栈为空则返回true,若栈非空则返回false;**
    public boolean isValid1(String S) {
        Deque<Character> stack= new LinkedList<>();
        for(int i=0;i<S.length();i++) {
            if(stack.peek()==null||S.charAt(i)!='c') {
                stack.push(S.charAt(i));
            }else {
                if(stack.peek()==null||stack.poll()!='b') {
                    return false;
                }
                if(stack.peek()==null||stack.poll()!='a') {
                    return false;
                }
            }
        }
        return stack.peek()==null;
    }

    public boolean isValid(String S) {
        while(S.contains("abc")){
            S = S.replace("abc", "");
        }
        return "".equals(S);
    }
}
