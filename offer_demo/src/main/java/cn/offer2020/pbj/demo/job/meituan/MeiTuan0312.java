package cn.offer2020.pbj.demo.job.meituan;

/**
 * @ClassName: MeiTuan0312
 * @Author: pbj
 * @Date: 2020/3/13 17:03
 * @Description: TODO 美团0312笔试题
 * https://www.nowcoder.com/discuss/380945?type=0&order=0&pos=20&page=1
 */
public class MeiTuan0312 {

    //1. 两行长度均为 n 字符串，仅包含字符'X`(代表障碍物)和'.'(可以通行)，初始在左上角(1, 1)，想到右下角(2, n)去，
    // 求方案数，如果不能到(2, n) 输出-1。数据范围：1\leq n \leq 501≤n≤50。
    //直接遇到两个点乘2就行
    public static int meiTuan01(String s1, String s2, int n) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        if (c1[0] == 'x' || c2[n - 1] == 'x') {
            return -1;
        }
        char[][] c = new char[3][n + 1];
        for (int i = 1; i <= n; i++) {
            c[1][i] = c1[i - 1];
            c[2][i] = c2[i - 1];
        }
        int count = 1;
        for (int j = 1; j < n; j++) {
            if (c[2][j + 1] == '.' && c[1][j + 1] == '.') {
                count = count * 2;
            } else if (c[2][j+1] == 'x' && c[1][j + 1] == 'x') {
                return -1;
            }
        }
        return count;
    }

    //2. 一个包含 n 个元素的数组，每个元素值是[L, R]的范围内的，求满足 n 个元素之和是 k 的倍数的数组个数。
    // 数据范围：1\leq n\leq 50000, 1\leq k\leq 10, 1\leq L \leq R \leq 1e91≤n≤50000,1≤k≤10,1≤L≤R≤1e9。
    public static int meiTuan02(int n, int x, int[] nums) {
        int[] dp = new int[1001];
        for (int i = 0; i < n; i++) {
            dp[nums[i]] = dp[nums[i]] + 1;
            if ((nums[i] | x) != nums[i]) {
                dp[nums[i]|x] = dp[nums[i]|x] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 1001; i++) {
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    //n位数组，数组数的值在L和R之间，并且数组每一位数的和为k的倍数
    //用 dp[i][j] 表示和模 k 后是 j 的长度为 i 的数组个数。需要先算下[L, R] 中 模 k 后是 j 的数字个数：
    // num[j] = (R-j)/k - (L-1-j)/k。时间复杂度：O(nk)O(nk)。
    public static int meiTuan03(int n,int k,int L,int R) {
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(meiTuan01("..x.x", "xx...", 5));//2
        System.out.println(meiTuan02(5,2,new int[]{3,1,3,2,5}));
    }
}
