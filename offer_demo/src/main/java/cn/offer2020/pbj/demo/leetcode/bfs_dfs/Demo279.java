package cn.offer2020.pbj.demo.leetcode.bfs_dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName: Demo279
 * @Author: pbj
 * @Date: 2020/4/9 13:15
 * @Description: TODO 279. 完全平方数
 */
public class Demo279 {

    //dp
    public int numSquares1(int n) {
        int[] dp = new int[n + 1];
        for(int i = 1; i <= n; i++){
            int val = i;
            for(int j = 1; i - j * j >= 0; j++){
                val = Math.min(val, dp[i - j * j] + 1);
            }
            dp[i] = val;
        }
        return dp[n];
    }

    //BFS
    public int numSquares(int n) {
        List<Integer> squ = generateSqu(n);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] marked = new boolean[n+1];
        queue.add(n);
        marked[n] = true;
        int level = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            level++;
            while(size-->0){
                int cur = queue.poll();
                for(int s:squ){
                    int next = cur -s;
                    if(next<0){
                        break;
                    }
                    if(next==0){
                        return level;
                    }
                    if(marked[next]){
                        continue;
                    }
                    marked[next] = true;
                    queue.add(next);
                }
            }
        }
        return n;
    }
    private List<Integer> generateSqu(int n){
        List<Integer> squ = new ArrayList<>();
        int square = 1;
        int diff = 3;
        while(square<=n){
            squ.add(square);
            square = square + diff;
            diff = diff + 2;
        }
        return squ;
    }
}
