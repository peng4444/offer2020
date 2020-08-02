package cn.offer2020.pbj.demo.leetcode.dp;

import java.util.Arrays;

/**
 * @pClassName: Demo174
 * @author: pengbingjiang
 * @create: 2020/8/2 12:06
 * @description: TODO 174.地下城游戏
 */
public class Demo174 {
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon.length==0||dungeon[0].length==0) return 0;
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        int row = dungeon.length;
        int col = dungeon[0].length;
        for(int i = row-1;i>=0;i--){
            for(int j= col-1;j>=0;j--){
                if(i==row-1&&j==col-1){
                    dp[i][j] = Math.max(1,1-dungeon[i][j]);
                }else if(i==row-1){
                    dp[i][j] = Math.max(1,dp[i][j+1]-dungeon[i][j]);
                }else if(j==col-1){
                    dp[i][j] = Math.max(1,dp[i+1][j]-dungeon[i][j]);
                }else{
                    dp[i][j] = Math.max(1,Math.min(dp[i+1][j],dp[i][j+1])-dungeon[i][j]);
                }
            }
        }
        return dp[0][0];
    }

    public static int calculateMinimumHP1(int[][] dungeon) {
        int[][] dp = new int[dungeon.length + 1][dungeon[0].length + 1];
        for(int i = 0; i < dungeon.length + 1; i++){
            Arrays.fill(dp[i], 9999);
        }
        dp[dungeon.length - 1][dungeon[0].length] = 1;
        dp[dungeon.length][dungeon[0].length - 1] = 1;
        for(int i = dungeon.length - 1; i >= 0; i--){
            for(int j = dungeon[0].length - 1; j >= 0; j--){
                dp[i][j] = Math.max(Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }
}
