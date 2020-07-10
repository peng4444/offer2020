package cn.offer2020.pbj.demo.leetcode.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @pClassName: Demo842
 * @author: pengbingjiang
 * @create: 2020/7/10 22:08
 * @description: TODO 842.将数组拆分成斐波那契序列
 */
public class Demo842 {
    public List<Integer> splitIntoFibonacci1(String S) {
        LinkedList<Integer> res=new LinkedList<>();
        dfs(S,0,res);
        return res;
    }

    public boolean dfs(String S,int index,List<Integer> lis){
        if (index == S.length()) {
            return lis.size()>2;
        }
        for (int i=index+1;i<=S.length();i++) {
            String temp=S.substring(index,i);
            //长度大于10,或者Long解析出来大于INT_MAX了就直接break
            if (S.charAt(index) == '0' && i>index+1 || temp.length()>10 || Long.valueOf(temp)>Integer.MAX_VALUE) {
                break;
            }
            int str=Integer.valueOf(temp);
            int one=lis.size()>=2 ? lis.get(lis.size()-1):-1;
            int two=lis.size()>=2 ? lis.get(lis.size()-2):-1;
            lis.add(str);
            if ((one==-1 || two==-1 || one+two==str) && dfs(S,i,lis)) {
                return true;
            }
            lis.remove(lis.size()-1);
        }
        return false;
    }
    //DFS + 剪枝
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> ans = new ArrayList<>();
        return dfs(ans,0, S, 0, 0, 0) ? ans : new ArrayList<>();
    }
    /**
     * @p : 当前指针指向数组的索引
     * @s : 字符串
     * @pre1 : 前面隔一个的数
     * @pre2 : 前一个数
     * @deep : 当前是第几个数
     **/
    public boolean dfs(List<Integer> ans,int p, String s, int pre1, int pre2, int deep) {
        int length = s.length();
        if (p == length)return deep >= 3;

        for (int i = 1; i <= 11; i++) {
            //超出长度或者以0开头直接break;
            if (p + i > length || (s.charAt(p) == '0' && i > 1))break;
            //截取字符串
            String sub = s.substring(p, p + i);

            long numL = Long.parseLong(sub);
            //判断是否超出范围,或者deep不是0,1却大于他的前两个数之和
            if (numL > Integer.MAX_VALUE ||
                    (deep != 0 && deep != 1 && numL > (pre1 + pre2)))break;
            //转成int
            Integer num = (int) numL;
            //满足条件的数,递归加回溯
            if (deep == 0 || deep == 1 || num.equals(pre1 + pre2)) {
                ans.add(num);
                if (dfs(ans,p + i, s, pre2, num, deep + 1))return true;
                ans.remove(num);
            }
        }
        return false;
    }

    //暴力法 数组的前两个元素唯一决定了后面的数组元素。
    public List<Integer> splitIntoFibonacci0(String S) {
        int N = S.length();
        for (int i = 0; i < Math.min(10, N); ++i) {
            if (S.charAt(0) == '0' && i > 0) break;
            long a = Long.valueOf(S.substring(0, i+1));
            if (a >= Integer.MAX_VALUE) break;

            search: for (int j = i+1; j < Math.min(i+10, N); ++j) {
                if (S.charAt(i+1) == '0' && j > i+1) break;
                long b = Long.valueOf(S.substring(i+1, j+1));
                if (b >= Integer.MAX_VALUE) break;

                List<Integer> fib = new ArrayList();
                fib.add((int) a);
                fib.add((int) b);

                int k = j + 1;
                while (k < N) {
                    long nxt = fib.get(fib.size() - 2) + fib.get(fib.size() - 1);
                    String nxtS = String.valueOf(nxt);

                    if (nxt <= Integer.MAX_VALUE && S.substring(k).startsWith(nxtS)) {
                        k += nxtS.length();
                        fib.add((int) nxt);
                    }
                    else continue search;
                }
                if (fib.size() >= 3) return fib;
            }
        }

        return new ArrayList<Integer>();
    }
}
