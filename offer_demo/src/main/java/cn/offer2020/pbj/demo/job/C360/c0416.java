package cn.offer2020.pbj.demo.job.C360;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @ClassName: c0416
 * @Author: pbj
 * @Date: 2020/4/17 11:21
 * @Description: TODO 360笔试 4.16
 * 链接：https://www.nowcoder.com/discuss/409909?type=1
 * 来源：牛客网
 */
public class c0416 {
    /* *
     * 功能描述:1.最强的不一定是最后的赢家。
//         输入
//        输入第一行包含两个正整数n，m，分别代表参赛选手数量和取得连胜的要求。(1<=n<=100000，1<=m<=10^9)
//        输入第二行包含n个正整数，中间用空格隔开，第i个数表示队伍的第i位选手的战斗力，整体是一个1~n的排列。
//        输出
//        输出仅包含一个正整数，表示截止到游戏终止，共进行多少场比赛。
//        样例输入
//        4 2
//        1 3 2 4
//        样例输出
//        2
     * @param: []
     * @return: void
     * @auther: pbj
     * @date: 2020/4/17 11:22
     */
    public void demo1() {
        Deque<Integer> q=new LinkedList<>();
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        long m=in.nextLong();
        long[] map=new long[n+1];
        for(int i=0; i<n; i++){
            q.addLast(in.nextInt());
        }
        if(n==1) System.out.println(0);
        else{
            int cnt=0;
            while (true){
                cnt++;
                int first=q.pollFirst();
                int senond=q.pollFirst();
                if(first<senond){
                    map[senond]++;
                    if(map[senond]==m) break;
                    q.addFirst(senond);
                    q.addLast(first);
                }else{
                    map[first]++;
                    if(map[first]==m) break;
                    q.addFirst(first);
                    q.addLast(senond);
                }
            }
            System.out.println(cnt);
        }
    }
    /* *
     * 功能描述:
     * 作者：normal_yuan
给定一个数a0，定义如下随机序列a1,a2,...an。
1、从闭区间[0,a0]中等概率随机选出一个整数k0，令a1=a0-k0。
2、得到随机数a1之后，再从闭区间[0,a1]中等概率随机选出一个整数k1，再令 a2=a1-k1。
3、一般地，得到随机数ai之后，再从闭区间[0,ai]中等概率随机选出一个整数ki，令ai+1=ai-ki。
问an=0的概率是多少?
//                输入
//        输入两个整数n,a0(1≤n,a0≤100)。
//        输出
//        输出概率，小数点后四舍五入保留5位小数。
//        样例输入
//        3 3
//        样例输出
//        0.72049
//        提示
//        输入样例2
//        1 3
//        输出样例2
//        0.25000
//        输入样例3
//        100 3
//        输出样例3
//        1.00000
     * @param: []
     * @return: void
     * @auther: pbj
     * @date: 2020/4/17 11:34
     */
    private static double res=0;
    public void demo2() {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int a=in.nextInt();
        helper(n,a,1);
        System.out.println(String.format("%.5f",res));
    }
    public static void helper(int n, int a,double pro){
        if(n==0){
            if(a==0) res+=pro;
            return;
        }
        for(int i=0; i<=a; i++){
            double tmp=pro;
            pro*=1/(double)(a+1);
            helper(n-1, a-i, pro);
            pro=tmp;
        }
    }
}
