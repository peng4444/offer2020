package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo204
 * @Author: pbj
 * @Date: 2020/4/14 17:11
 * @Description: TODO 204. 计数质数 统计所有小于非负整数 n 的质数的数量。
 */
public class Demo204 {
    public int countPrimes(int n) {
        boolean[] notPrimes = new boolean[n+1];
        int count = 0;
        for(int i = 2;i<n;i++){
            if(notPrimes[i]){
                continue;
            }
            count++;
            for(long j = (long)(i)*i;j<n;j+=i){
                notPrimes[(int)j] = true;
            }
        }
        return count;
    }
}
