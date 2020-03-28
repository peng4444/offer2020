package cn.offer2020.pbj.demo.job.alibaba;

import java.util.LinkedList;

/**
 * @ClassName: JosephRingProblem
 * @Author: pbj
 * @Date: 2020/2/2 10:45
 * @Description: TODO
 * [阿里面试：约瑟夫环问题](https://www.cnblogs.com/kubidemanong/p/12230230.html)
 * 问题描述：编号为 1-N 的 N 个士兵围坐在一起形成一个圆圈，从编号为 1 的士兵开始依次报数（1，2，3...这样依次报），
 * 数到 m 的 士兵会被杀死出列，之后的士兵再从 1 开始报数。直到最后剩下一士兵，求这个士兵的编号。
 */
public class JosephRingProblem {


    //方法一：数组
    public static int ysfh1(int n,int m) {
        boolean[] judge = new boolean[n];
        int begin = 0;
        while (n > 1) {
            int end = (begin+3)%n;
            if (!judge[end]) {
                judge[end] = true;
            } else {
                int i = end +1;
                while (judge[i % n]) {
                    i++;
                }
                judge[i] = false;
                end = i;
            }
            begin = end + 1;
            n--;
        }
        for (int i = 0; i<judge.length;i++){
            return i+1;
        }
        return 0;
    }
    //方法二：环形链表
    public static int ysfh(int num){
        LinkedList<Integer> list=new LinkedList<>();
        for (int i = 1; i <=num; i++)list.add(i);
        int i=0;
        while (num>1){
            i=(i+3)%list.size();//从0开始，所以是3
            list.remove(i);
            num--;
        }
        return list.peek();
    }
    //方法三：递归
    public static int helper(int n){
        LinkedList<Integer>list=new LinkedList<>();
        for (int i = 1; i <= n; i++)list.add(i);
        return ysfh(0,list);
    }
    public static int ysfh(int begin,LinkedList<Integer>list){
        if(list.size()==1)return list.peek();
        int i=(begin+3)%list.size();
        list.remove(i);
        return ysfh(i,list);
    }
}
