package cn.offer2020.pbj.demo.leetcode.string;

/**
 * @pClassName: Demo1278
 * @author: pengbingjiang
 * @create: 2020/7/27 20:45
 * @description: TODO   1278.分割回文串III
 */
public class Demo1278 {
    public int palindromePartition(String s, int K) {

        int len = s.length();

        if(len <= K){
            return 0;
        }

        //dp[j][k] 表示将 s 中 [0, j] 子字符串划分为 k 个回文串所需的最少修改数
        int[][] dp = new int[len][K + 1];

        //预处理，提前处理好所有子串变成回文串需要的步数
        char[] chs = s.toCharArray();
        int[][] prePro = new int[len][len];
        for(int i = 0; i < len; i++){
            for(int j = i + 1; j < len; j++){
                prePro[i][j] = helper(chs, i, j);
            }
        }

        /*
                dp[j][k] 表示将 s 中 [0, j] 子字符串划分为 k 个回文串所需的最少修改数
                即将 [0, m) 分成 k - 1 块， [m, j] 分成 1 块

                m 需要满足什么条件？
                比如 k - 1 = 1，即 [0, m) 至少需要存在一个字母，即 m = 1，即 m >= k - 1
                因为 [m, j] 必须存在存在一个字母，即 m <= j

        */
        //处理 k == 1 的情况
        for(int i = 0; i < len; i++){
            dp[i][1] = prePro[0][i];
        }
        for(int k = 2; k <= K; k++){
            for(int j = k; j < len; j++){
                /*
                当使用到某个值时，直接初始化为 MIN_VALUE，而不提前初始化了
                原因：
                如果修改步数为 0 的情况下，dp[j][k] 不需要修改，值为 MAX_VALUE,
                而前面 dp[m - 1][k - 1]  也不需要修改 值为 MAX_VALUE，
                当 prePro[m][j] = 1 时，那么导致 dp[m - 1][k - 1] + prePro[m][j] 溢出变成 Integer.MIN_VALUE

                因此，为了避免这种情况，以后直接在这里用到就初始化就好
                */
                dp[j][k] = Integer.MAX_VALUE;
                for(int m = k - 1; m <= j; m++){
                    dp[j][k] = Math.min(dp[j][k], dp[m - 1][k - 1] + prePro[m][j]);
                }
            }
        }
        return dp[len - 1][K];
    }
    private int helper(char[] chs, int left, int right){
        int c = 0;
        while(left < right){
            if(chs[left] != chs[right]){
                c++;
            }
            left++;
            right--;
        }
        return c;
    }
}
