package cn.offer2020.pbj.demo.leetcode.hard;

import java.util.*;

/**
 * @pClassName: Demo301
 * @author: pengbingjiang
 * @create: 2020/7/11 17:25
 * @description: TODO 301. 删除无效的括号
 * 删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。
 */
public class Demo301 {
    public List<String> removeInvalidParentheses2(String s) {
        List<String> res = new ArrayList<>();
        int l = 0;
        int r = 0;
        for(char c : s.toCharArray()){
            if(c == '('){
                l++;
            }else if(l == 0 && (c==')')){
                r++;
            }else if(c == ')'){
                l--;
            }
        }
        dfs(s, l, r, 0, res);
        return res;
    }

    public boolean isValid2(String s){
        int cnt = 0;
        for(char c : s.toCharArray()){
            if(c == '('){
                cnt++;
            }
            if(c == ')'){
                cnt--;
            }
            if(cnt < 0){
                return false;
            }
        }
        return cnt == 0;
    }

    public void dfs(String s, int l, int r, int start, List<String> res){
        if(l == 0 && r == 0){
            if(isValid2(s)) res.add(s);
            return;
        }
        for(int i = start; i < s.length(); i++){
            if(i != start && s.charAt(i) == s.charAt(i - 1)) continue;
            if(s.charAt(i) == '(' || s.charAt(i) == ')'){
                if(r > 0){
                    dfs(s.substring(0, i) + s.substring(i + 1), l, r - 1, i, res);
                }else if(l > 0){
                    dfs(s.substring(0, i) + s.substring(i + 1), l - 1, r, i , res);
                }
            }
        }
    }
    public List<String> removeInvalidParentheses1(String s) {
        Set<String> level = new HashSet<>();
        level.add(s);
        List<String> res = new ArrayList<>();
        while (res.isEmpty()) {
            for (String str : level) {
                if (isValid1(str)) res.add(str);
            }
            if (!res.isEmpty()) return res;
            Set<String> level2 = new HashSet<>();
            for(String str : level) {
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == '(' || str.charAt(i) == ')') {
                        String subs = str.substring(0, i) + str.substring(i + 1);
                        level2.add(subs);
                    }
                }
            }
            level = level2;
        }
        return res;
    }

    public boolean isValid1(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }
    //BFS
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s.equals("()") || s.equals("")) {
            result.add(s);
            return result;
        }

        Deque<String> queue = new ArrayDeque<>();
        queue.offer(s);
        HashSet<String> set = new HashSet<>();  //用于存储裁剪后的元素，防止重复元素加入队列
        boolean isFound = false;    //判断是否找到了有效字符串

        while (!queue.isEmpty()) {  //队列不为空
            String curr = queue.poll();
            if (isValid(curr)) {
                result.add(curr);
                isFound = true;
            }
            if (isFound) {  //找到后不再进行裁剪
                continue;
            }
            //裁剪过程
            for (int i = 0; i < curr.length(); i++) {
                if (curr.charAt(i) == '(' || curr.charAt(i) == ')') {   //只对'('或')'进行裁剪
                    String str;
                    if (i == curr.length()-1) {
                        str = curr.substring(0, curr.length()-1);
                    } else {
                        str = curr.substring(0, i) + curr.substring(i+1);
                    }
                    if (set.add(str)) { //如果集合中还未有该字符串
                        queue.offer(str);
                    }
                }
            }
        }

        if (result.isEmpty()) {
            result.add("");
        }
        return result;
    }

    private static boolean isValid(String s) {
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i);
            if (curr == '(') {
                left++;
            } else if (curr == ')') {
                if (left != 0) {
                    left--;
                } else {
                    return false;
                }
            }
        }
        return left == 0;
    }
}
