package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @pClassName: Demo1137
 * @author: pengbingjiang
 * @create: 2020/7/10 10:24
 * @description: TODO 1137. 第 N 个泰波那契数
 */
public class Demo1137 {
    public int tribonacci(int n) {
        if(n==0) return 0;
        else if(n==1||n==2) return 1;
        int[] trib = new int[n+1];
        trib[0] = 0;
        trib[1] = 1;
        trib[2] = 1;
        for(int i = 3;i<=n;i++){
            trib[i] = trib[i-1]+trib[i-2]+trib[i-3];
        }
        return trib[n];
    }

    public int tribonacci1(int n) {
        if(n<3) return n==0?0:1;
        int temp,x = 0,y = 1,z = 1;
        for(int i = 3; i <= n; i++){
            temp = x + y + z;
            x = y;
            y = z;
            z = temp;
        }
        return z;
    }
}
