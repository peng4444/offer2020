package cn.offer2020.pbj.demo.leetcode.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: Demo873
 * @Author: pbj
 * @Date: 2020/1/3 10:47
 * @Description: TODO 873.最长的斐波那契子序列的长度 数组中的数满足斐波那契数的最长长度
 */
public class Demo873 {
    /* *
     * 功能描述: 使用 Set 的暴力法
     * 时间复杂度O(N^2 * logM) 空间复杂度O(N)
     * @param: [A]
     * @return: int
     * @auther: pbj
     * @date: 2020/1/3 10:53
     */
    public int lenLongestFibSubseq(int[] A) {
        int N = A.length;
        Set<Integer> set = new HashSet<>();
        for(int x:A) set.add(x);//将数组加入到集合中

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int x = A[j], y = A[i] + A[j];
                int length = 2;
                while (set.contains(y)) {
                    int tmp = y;
                    y += x;
                    x = tmp;
                    ans = Math.max(ans, ++length);
                }
            }
        }
        return ans>=3 ? ans : 0;
    }

    public int lenLongestFibSubseq2(int[] A) {
        int dp[][]=new int[A.length][A.length];
        Map<Integer,Integer> ans=new HashMap<>();
        for(int i=0;i<A.length;i++){
            ans.put(A[i],i);
        }
        int max=0;
        for(int i=2;i<A.length;i++){
            for(int j=i-1;j>=0;j--){
                if(ans.containsKey(A[i]-A[j])&&ans.get(A[i]-A[j])<j){
                    dp[j][i]=Math.max(dp[ans.get(A[i]-A[j])][j]+1,3);
                    max=Math.max(max,dp[j][i]);
                }
            }
        }
        return max;
    }
    /* *
     * 功能描述: dp 看不懂
     * 时间复杂度O(N^2) 空间复杂度O(N)
     * @param: [A]
     * @return: int
     * @auther: pbj
     * @date: 2020/1/3 11:00
     */
    public int lenLongestFibSubseq1(int[] A) {
        int N = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(A[i], i);
        }
        Map<Integer, Integer> longest = new HashMap<>();
        int ans = 0;
        for (int k = 0; k < N; k++) {
            for (int j = 0; j < k; j++) {
                int i = map.getOrDefault(A[k] - A[j], -1);
                if (i >= 0 && i < j) {
                    int cand = longest.getOrDefault(i*N+j,2)+1;
                    longest.put(j * N + k, cand);
                    ans = Math.max(ans, cand);
                }
            }
        }
        return ans>=3? ans:0;
    }
}
