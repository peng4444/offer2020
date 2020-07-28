package cn.offer2020.pbj.demo.job.alibaba;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @pClassName: Demo0727
 * @author: pengbingjiang
 * @create: 2020/7/28 10:34
 * @description: TODO
 * [阿里7.27笔试思路](https://www.nowcoder.com/discuss/460768)
 */
public class Demo0727 {
    /**
    *
     * 纸上写了一个单调非递减的数字序列，每个人依次选择一个数字，然后把这个数字第一次出现位置及其之前的数字都删掉，直到谁把序列删除空谁就赢了，
     * niuniu先手，niumei后手，谁赢打印谁的名字。
     * 输入：
     * T 表示有几组数据
     * n 表示每组序列中有几个数
     * ***** 接下来一行为序列
     * 样例：
     * 1
     * 6
     * 111222
     * 输出：niuniu
     * 原因：niuniu选2，序列变为22；niumei选2，序列变为2；niuniu选2，序列变为空，niuniu赢
     * 只有当所有数字都出现偶数次时候第二个人才能赢，其他情况都是第一个人赢。
    */
    public static void main1(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int T = sc.nextInt();
        for (int j = 0; j < T; j++) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            for (int i = 1; i < n; i++) {
                nums[i] = nums[i] & nums[i - 1];
            }
            if(nums[n-1]==0) System.out.println("niumei");
            else System.out.println("niuniu");
        }
    }

    /**
    *
     * 有个藏宝架有n层，每层的宝物数量不一，每个宝物都有其价值，现在要求拿出m个宝物，并且需要遵守规则：
     * 每次只能拿选定层的两端的宝物
     * 要拿出的m个宝物的总价值是各种方案里最大的
     * 输入：
     * n m
     * 下面每行代表每层，且第一个数是这层宝物的数量k，后面的则是k个宝物的价值
     * 4 1 2 4 5
     * 5 1 2 4 5 5
     * 样例：
     * 2 3
     * 2 3 2
     * 4 1 4 1 5
     * 输出：5+3+2=10
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int curr = sc.nextInt();
            int sum = 0;
            int[] currNum = new int[curr];
            for (int j = 0; j < curr; j++) {
                currNum[j] = sc.nextInt();
                sum = sum + currNum[j];
            }

        }
    }
}
