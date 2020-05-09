package cn.offer2020.pbj.demo.leetcode.tree;

/**
 * @ClassName: Demo1359
 * @Author: pbj
 * @Date: 2020/5/9 12:05
 * @Description: TODO 1359. 有效的快递序列数目
 */
public class Demo1359 {
    public int countOrders(int n) {
        long last=1;
        long next=0;
        long sum=0;

        for(int i=2;i<=n;i++){
            int to=(i-1)*2+1;
            sum=to*(to+1)/2;
            next=(last*sum)%1000000007;
            last=next;
        }
        return (int)(last%1000000007);
    }
}
