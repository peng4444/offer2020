package cn.offer2020.pbj.demo.job.vivo;

/**
 * @ClassName: VivoDemo
 * @Author: pbj
 * @Date: 2020/3/8 22:28
 * @Description: TODO
 * vivo38-https://www.nowcoder.com/discuss/377317?type=6
 */
public class VivoDemo {

    /* *
     * 功能描述: 3.8 给定任意正整数n,寻找并输出最小的正整数m(m>9),使得m的各位乘积对于n,不存在输出-1
     * @param: 36
     * @return: 49
     * @auther: pbj
     * @date: 2020/3/8 22:31
     * 分解因子，且因子都是一位数（小于10）
     */
    public int vivo38_1(int n) {
        if (n < 10) {
            return n;
        }
        for (int i = 9; i > 0; i--) {
            if (n % i == 0) {
                return vivo38_1(n / i) * 10 + 1;
            }
        }
        return -1;
    }

    /* *
     * 功能描述: 第一天生产1，第二，三天生产2，第四五六天生产3，....求第n天总共生产
     * @param: [n] 11
     * @return: int 35
     * @auther: pbj
     * @date: 2020/3/8 23:39
     */
    //看不懂
    public int vivo_38_2(int n) {
        int i = 1;
        int sum = 0;
        while (n - i > 0) {
            sum = sum + i * i;
            n = n - i;
            i++;
        }
        sum = sum + n * i;
        return sum;
    }

    public int vivo_38_2_1(int n) {
        int[] dp = new int[n];
        int day_produce = 1;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (i > 1) {
                dp[i - 1] = dp[i - 2] + day_produce;
            } else dp[i - 1] = 1;
            count++;
            if(count==day_produce){
                count = 0;
                day_produce++;
            }
        }
        return dp[n - 1];
    }

    // vivo_38_3 leetcode351  https://blog.csdn.net/qq508618087/article/details/51758481
}
