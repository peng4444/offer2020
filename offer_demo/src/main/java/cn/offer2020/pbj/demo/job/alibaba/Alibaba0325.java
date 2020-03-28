package cn.offer2020.pbj.demo.job.alibaba;

/**
 * @ClassName: Alibaba0325
 * @Author: pbj
 * @Date: 2020/3/26 10:06
 * @Description: TODO 2020年3月25日阿里笔试题
 * [2020年3月25日阿里笔试题](https://blog.csdn.net/m0_38065572/article/details/105101287)
 * [阿里笔试325](https://www.nowcoder.com/discuss/391606?type=1)
 * [阿里巴巴3.20笔试题（Java）](https://www.nowcoder.com/discuss/388388?type=1)
 */
public class Alibaba0325 {

    //1.给定一个数组n，然后给三个长度为n的数组，可以从这三个数组中选出一个长度为n的数组，
    // 第i个位置需要是从给出的三个数组第i个位置选择的，然后要求使这个数组后一项减前一项的绝对值之和最小。
    //输入示例：：
    //5 9  5 4  4
    //4 7  4 10 3
    //2 10 9 2  3
    //这里可以选择5 7 5 4 4，所以输出等于|7-5|+|5-7|+|4-5|+|4-4|=5。所以输出就是5。
    public int minN(int[][] nums) {
        int len = nums[0].length;
        int[][] dp = new int[len][3];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (k == 0) {
                        dp[i][j] = Math.abs(nums[j][i] - nums[k][i - 1]) + dp[i - 1][k];
                    } else {
                        dp[i][j] = Math.min(Math.abs(nums[j][i] - nums[k][i - 1]) + dp[i - 1][k], dp[i][j]);
                    }
                }
            }
        }
        return Math.min(dp[len - 1][0], Math.min(dp[len - 1][1], dp[len - 1][2]));
    }

    //给出一个二维矩阵，这个矩阵的每一行和每一列都是一个独立的等差数列，其中一些数据缺失了，现在需要推理隐藏但是可以被唯一确定的数字，然后对输入的查询进行回答。
    //
    //输入描述：
    //第一行，n,m,q分别表示矩阵的行数，列数和查询的条数。
    //接下来的n行，每行m个数表示这个矩阵，0表示缺失数据。
    //接下来q行，每行两个数字i,j表示对矩阵中第i行第j列的数字进行查询。
    //
    //输出描述：
    //如果可以确定该位置的数字，则输出数字，如果不能确定则输出UNKNOWN。
    //
    //输入示例：
    //2 3 6
    //1 0 3
    //0 0 0
    //1 1
    //1 2
    //1 3
    //2 1
    //2 2
    //2 3
    //
    //输出示例：
    //1
    //2
    //3
    //Unknown
    //Unknown
    //Unknown

    public static void solve(int[][] nums){
        int r = nums.length, c = nums[0].length;
        boolean[] flag = new boolean[r+c];
        while (rowSolve(nums,flag)||colunm(nums,flag)){

        }
    }

    public static boolean rowSolve(int[][] nums, boolean[]flag){
        int r = nums.length, c = nums[0].length;
        boolean f = false;
        for (int i = 0; i < r; i++) {
            if (!flag[i]){
                int[] pre = new int[2];
                for (int j = 0; j < c; j++) {
                    if (nums[i][j]!=0&&pre[1]!=0){
                        int dif = (nums[i][j] - pre[1])/(j - pre[0]);
                        for (int k = j+1; k < c; k++) {
                            nums[i][k] = nums[i][k-1] + dif;
                        }
                        for (int k = j-1; k >= 0; k--) {
                            nums[i][k] = nums[i][k+1] - dif;
                        }
                        flag[i] = true;
                        f = true;
                        break;
                    }else if (nums[i][j]!=0){
                        pre[0] = j;
                        pre[1] = nums[i][j];
                    }
                }
            }
        }
        return f;
    }


    public static boolean colunm(int[][] nums, boolean[] flag){
        int r = nums.length, c = nums[0].length;
        boolean f = false;
        for (int j = 0; j < c; j++) {
            if (!flag[r+j]){
                int[]pre = new int[2];
                for (int i = 0; i < r; i++) {
                    if (nums[i][j]!=0&&pre[1]!=0){
                        int dif = (nums[i][j] - pre[1])/(i - pre[0]);
                        for (int k = i+1; k < r; k++) {
                            nums[k][j] = nums[k-1][j]+dif;
                        }
                        for (int k = i-1; k >= 0; k--) {
                            nums[k][j] = nums[k+1][j]-dif;
                        }

                        f = true;
                        flag[r+j]=true;
                        break;
                    }else if (nums[i][j]!=0){
                        pre[0] = i;
                        pre[1] = nums[i][j];
                    }
                }
            }
        }
        return f;
    }
}
