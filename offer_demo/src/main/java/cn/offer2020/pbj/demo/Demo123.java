package cn.offer2020.pbj.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @pClassName: Demo123
 * @author: pengbingjiang
 * @create: 2020/9/12 11:28
 * @description: TODO
 */
public class Demo123 {
    public long countWays (int n) {
        // write code here
        long[] dp = new long[n+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        if(n<=2) return n;
        for(int i = 3;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    public int completeCircuit (int[] powers, int[] costs) {
        // write code here
        int res = 0;
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i<powers.length;i++){
            if(powers[i]<costs[i]){
                list.add(i);
            }
        }
        for(int i = 0;i<list.size();i++){
            int start = list.get(i);
            if(help(start,powers,costs)){
                return start;
            }
        }
        return -1;
    }

    public static boolean help(int start,int[] power,int[] costs){
        int currRest = costs[start] - power[start];
        for(int i = start+1;i<power.length;i++){
            int rest = costs[i] - power[i];
            currRest = currRest+rest;
            if(currRest<0){
                return false;
            }
        }
        for(int i = power.length;i>start;i--){
            int rest = costs[i] - power[i];
            currRest = currRest+rest;
            if(currRest<0){
                return false;
            }
        }
        return true;
    }
}
