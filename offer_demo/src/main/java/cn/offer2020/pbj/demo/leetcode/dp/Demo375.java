package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @pClassName: Demo375
 * @author: pengbingjiang
 * @create: 2020/7/9 11:20
 * @description: TODO 375. 猜数字大小 II
 */
public class Demo375 {
    //dp
    public int getMoneyAmount(int n) {
        /**
         dp[i][j]表示从[i,j]中猜出正确数字所需要的最少花费金额.(dp[i][i] = 0)
         假设在范围[i,j]中选择x, 则选择x的最少花费金额为: max(dp[i][x-1], dp[x+1][j]) + x
         用max的原因是我们要计算最坏反馈情况下的最少花费金额(选了x之后, 正确数字落在花费更高的那侧)

         初始化为(n+2)*(n+2)数组的原因: 处理边界情况更加容易, 例如对于求解dp[1][n]时x如果等于1, 需要考虑dp[0][1](0不可能出现, dp[0][n]为0)
         而当x等于n时, 需要考虑dp[n+1][n+1](n+1也不可能出现, dp[n+1][n+1]为0)

         如何写出相应的代码更新dp矩阵, 递推式dp[i][j] = max(max(dp[i][x-1], dp[x+1][j]) + x), x~[i:j], 可以画出矩阵图协助理解, 可以发现
         dp[i][x-1]始终在dp[i][j]的左部, dp[x+1][j]始终在dp[i][j]的下部, 所以更新dp矩阵时i的次序应当遵循bottom到top的规则, j则相反, 由于
         i肯定小于等于j, 所以我们只需要遍历更新矩阵的一半即可(下半矩阵)
         **/
        int[][] dp = new int[n+2][n+2];
        for(int i = n; i >= 1; --i) {
            for(int j = i; j <= n; ++j) {
                if(i == j)
                    dp[i][j] = 0;
                else {
                    dp[i][j] = Integer.MAX_VALUE;
                    for(int x = i; x <= j; ++x)
                        dp[i][j] = Math.min(dp[i][j], Math.max(dp[i][x-1], dp[x+1][j]) + x);
                }
            }
        }
        return dp[1][n];
    }

    public int calculate2(int low, int high) {
        if (low >= high)
            return 0;
        int minres = Integer.MAX_VALUE;
        for (int i = (low + high) / 2; i <= high; i++) {
            int res = i + Math.max(calculate2(i + 1, high), calculate2(low, i - 1));
            minres = Math.min(res, minres);
        }
        return minres;
    }
    //暴力优化
    public int getMoneyAmount2(int n) {
        return calculate2(1, n);
    }


    public int calculate1(int low, int high) {
        if (low >= high)
            return 0;
        int minres = Integer.MAX_VALUE;
        for (int i = low; i <= high; i++) {
            int res = i + Math.max(calculate1(i + 1, high), calculate1(low, i - 1));
            minres = Math.min(res, minres);
        }

        return minres;
    }
    //暴力求解
    public int getMoneyAmount1(int n) {
        return calculate1(1, n);
    }
}
