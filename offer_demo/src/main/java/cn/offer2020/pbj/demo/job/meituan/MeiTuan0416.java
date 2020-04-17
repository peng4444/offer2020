package cn.offer2020.pbj.demo.job.meituan;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @ClassName: MeiTuan0416
 * @Author: pbj
 * @Date: 2020/4/17 10:37
 * @Description: TODO 美团0416笔试
 * 链接：https://www.nowcoder.com/discuss/410187?type=0&order=3&pos=7&page=0
 * 来源：牛客网
 * [美团java后端实习 在线笔试 4.16](https://www.nowcoder.com/discuss/410088?type=2)
 */
public class MeiTuan0416 {
    /* *
     * 功能描述: 输出单科最优学生人数
     * n个学生，m个科目，现在学校要给优秀学生颁奖，评判标准是至少在一个科目上获得了最高分【并列第一的情况！！这个当时没考虑到】
     * 输入：第一行两个参数，分别是n：学生人数；m：科目数；接着下面有n行m列数据，每行数据表示每个同学每一门科目的成绩
     * 要考虑两个情况：一个是每个人只能算一次，还有种是并列第一的情况
     * @param: []
     * @return: void
     * @auther: pbj
     * @date: 2020/4/17 10:38
     */
    public void demo1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        if (n <= 0 || m <= 0) {
            System.out.println(0);
            return;
        }
        int[][] scores = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                scores[i][j] = sc.nextInt();
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int j = 0; j < m; j++) {
            int max = 0,maxIndex = 0;
            for (int i = 0; i < n; i++) {
                if (scores[i][j] > max) {
                    max = scores[i][j];
                    maxIndex = i;
                }
            }
            set.add(maxIndex);
        }
        System.out.println(set.size());
    }

    /* *
     * 功能描述: 输出循环结果的最小长度
     * 给定四个数a,b,m,x 然后有个运算法则是x=(a*x+b)%m，因为是取余，所以x是重复的，把重循环长度打印出来
     * 就是把题目给的循环实现了下，遇到一个已经出现过的x就结束循环，对循环次数进行计数，然后把类型改成了long，防止爆int，然后就能过了
     * @param: []
     * @return: void
     * @auther: pbj
     * @date: 2020/4/17 10:58
     */
    public void demo2() {
        Scanner sc = new Scanner(System.in);
        int a, b, m, x;
        a = sc.nextInt();
        b = sc.nextInt();
        m = sc.nextInt();
        x = sc.nextInt();
        boolean[] flag = new boolean[m];
        int ans = 0;
        while (true) {
            x = (int) (((long) a * (long) x + (long) b) % (long) m);
            if (flag[x] == true) {
                break;
            }
            ans++;
            flag[x] = true;
        }
        System.out.println(ans);
    }
    /* *
     * 功能描述: 输出第k小的有序数对
     * 第一行两个数n和k，n表示有多少个数字，k表示输出第k小的有序数对（m,n）。有序数对比较规则是第一个数大的大，第一个数相等则第二个数大的大。输出第k小的有序数对
     * @param: []
     * @return: void
     * @auther: pbj
     * @date: 2020/4/17 11:03
     */
    public void demo3() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        if (n <= 0 || k <= 0) {
            return;
        }
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        Arrays.sort(nums);

        // 看开头是什么
        int zhengshu = k / n, yushu = k % n;
        if (yushu == 0) {
            System.out.println("(" + nums[zhengshu - 1] + "," + nums[n - 1] + ")");
        }else {
            System.out.println("("+nums[zhengshu]+","+nums[yushu-1]+")");
        }
    }

    /* *
     * 功能描述: 伪中位数
     * 第一行两个数n和k，n表示有多少个数字，k是伪中位数。伪中位数定义：第 |_（n+1）/2_| 个数，|__| 表示向下取整。输出为至少需要增加多少个数才能使伪中位数为k
     * @param: []
     * @return: void
     * @auther: pbj
     * @date: 2020/4/17 11:04
     */
    public void demo4() {

    }
}
