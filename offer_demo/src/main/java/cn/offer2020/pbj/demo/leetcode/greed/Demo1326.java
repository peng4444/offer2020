package cn.offer2020.pbj.demo.leetcode.greed;

/**
 * @ClassName: Demo1326
 * @Author: pbj
 * @Date: 2020/4/24 14:50
 * @Description: TODO 1326. 灌溉花园的最少水龙头数目
 */
public class Demo1326 {
    public int minTaps(int n, int[] ranges) {
        int ans = 0,cur = 0;
        int[] r = new int[n+1];
        for(int i = 0;i<=n;i++){
            r[Math.max(0,i-ranges[i])] = Math.max(r[Math.max(0,i-ranges[i])],Math.min(n,i+ranges[i]));
        }
        for(int i = 1;i<=n;i++){
            r[i] = Math.max(r[i-1],r[i]);
        }
        while(cur<n){
            if(cur==r[cur]){
                return -1;
            }
            cur = r[cur];
            ans++;
        }
        return ans;
    }
}
