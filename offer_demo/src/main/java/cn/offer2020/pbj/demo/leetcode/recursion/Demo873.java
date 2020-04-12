package cn.offer2020.pbj.demo.leetcode.recursion;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: Demo873
 * @Author: pbj
 * @Date: 2020/1/3 10:47
 * @Description: TODO 最长的斐波那契子序列的长度 数组中的数满足斐波那契数的最长长度
 */
public class Demo873 {
    //DP, dp[i][j]表示以A[i] 和A[j]为结尾的子序列的长度；
//    class Solution {
//        public:
//        int lenLongestFibSubseq(vector<int>& A) {
//            int len= A.size();
//            int dp[len][len];
//
//            unordered_map<int,int> map;
//            for(int i=0; i<len; i++)
//                map[A[i]]=i;
//
//            int ans=0;
//            for (int j=1; j<len; j++)
//                for (int i=0; i<j; i++){
//                    dp[i][j]=2;
//                    if ( A[j]-A[i]<A[i]  && map.find(A[j]-A[i])!=map.end() ){
//                        int k=map[A[j]-A[i]];
//                        dp[i][j]=max(dp[i][j], 1+dp[k][i]);
//                    }
//                    ans=max(ans, dp[i][j]);
//                }
//            return (ans>2)? ans: 0;
//        }
//    };
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
}
