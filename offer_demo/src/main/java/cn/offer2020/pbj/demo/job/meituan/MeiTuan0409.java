package cn.offer2020.pbj.demo.job.meituan;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @ClassName: MeiTuan0409
 * @Author: pbj
 * @Date: 2020/4/9 23:20
 * @Description: TODO
 * [4.9 美团笔试AK](https://www.nowcoder.com/discuss/404217?type=2)
 * [4.9美团笔试五题解析](https://www.nowcoder.com/discuss/404360)
 */
public class MeiTuan0409 {
    //1.1.已知当前星期，时，分，求指定分钟前的星期，时，分
    public void demo1() {
        long yitian =24*60L;
        Scanner sc = new Scanner(System.in);
        long day = sc.nextLong();
        String str = sc.next();
        String[] split = str.split(":");
        long shi = Long.parseLong(split[0]);
        long fen = Long.parseLong(split[1]);
        long qian = sc.nextLong();
        long tot = 0;
        qian = qian % (7L * yitian);
        tot = yitian * (day - 1) + shi * 60L + fen;
        day = tot / yitian;
        day = day + 1;
        shi = (tot%(yitian))/60L;
        fen = (tot%(yitian))%60L;
        System.out.println(day);
        System.out.printf("%02d","%02d",shi,fen);
    }

    //2.有编号1-n的选手，给定出发排列顺序和到达排列顺序，只要存在一个人X，
    // 出发时X在Y前，到达时X在Y后，那么Y会受到嘉奖。求能受到嘉奖的总人数。
    public void demo2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();//出发顺序
        }
        for (int i = 0; i < n; i++) {
            map.put(a[i], i);
        }
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();//到达顺序
        }
        int min = 0;
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                min = map.get(b[i]);
                continue;
            }
            if (map.get(b[i]) > min) {
                ans++;
            }
            min = Math.max(min, map.get(b[i]));
        }
        System.out.println(ans);
    }

    //3.3. f(x)=累加[x/(k^(i-1)]（i=1...n,[]表示向下取整，括号内值为0停止累加），问令f(x)>=n满足的最小x
    public void demo3() {
        Scanner sc = new Scanner(System.in);
        long n=sc.nextLong();
        long k=sc.nextLong();
        long l=1;
        long r=n+1;
        while(l<r){
            long mid=(l+r)/2;

            if(judge(mid,n,k))r=mid;
            else l=mid+1;
        }
        System.out.println(l);
    }
    private static boolean judge(long x,long n,long k){
        long tmp=x;
        while(n>0){
            n-=x;
            x/=k;
            if(x<=0)break;
        }

        return n<=0;
    }

    //4.4.给定4个顶点S,A,B,C，任何两个顶点均有一条连线（本质为4点完全图），从S点出发，经过K步，有多少种走法能最终到达S
    public void demo4() {
        long mod = 1000000007L;

    }

    //5.5.给定一组字符串，三种操作：插入/删除编号为x的字符串，求指定文本串S和输入的字符串集合的匹配总次数
    public void demo5() {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        String s[]=new String[k+1];
        boolean de[]=new boolean[k+1];
        for(int i=1;i<=k;i++){
            s[i]=sc.next();
        }
        String str="";
        LinkedList<Integer> sum = new LinkedList<>();
        for(int kkk=0;kkk<n;kkk++){
            str=sc.next();

            if(str.charAt(0)=='-'){
                de[Integer.parseInt(str.substring(1,str.length()))]=true;
            }
            else if(str.charAt(0)=='+'){
                de[Integer.parseInt(str.substring(1,str.length()))]=false;
            }
            else{
                int ans=0;
                String mo = str.substring(1, str.length());
                for(int i=1;i<=k;i++){
                    if(de[i])continue;
                    int index=0;
                    while(true){
                        int tmp=mo.indexOf(s[i],index);
                        //System.out.println(tmp);
                        if(tmp==-1)break;
                        index=tmp+1;
                        ans++;
                    }
                }
                sum.add(ans);
            }
        }
        for (Integer integer : sum) {
            System.out.println(integer);
        }
    }
}
