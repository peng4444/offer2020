package cn.offer2020.pbj.demo.job.alibaba;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @pClassName: Alibaba0718
 * @author: pengbingjiang
 * @create: 2020/7/18 20:22
 * @description: TODO [阿里笔试两道题，分享下答案](https://www.nowcoder.com/discuss/454311)
 * [20200718阿里笔试第二题](https://www.cnblogs.com/ningxinjie/p/13334562.html)
 */
public class Alibaba0718 {
    //题目1:给定一个整数n, n >= 0 && n < (1 << 31), 数对(a, b), 使 (n ^ a ^ b) 取最大值, 求能使|a - b|取最小的对数。
    //0 -> 2, 100 -> 16
    //如果bit位是1, 那么1 ^ 0 ^ 0 = 1, 1 ^ 1 ^ 1 = 1, 相等差是不会变的, 所以 *2, 如果是0, 只有一种可能，由于对称性 *2, x = INT_MAX, 则对称的重合 /2
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = Integer.MAX_VALUE;
        while (n>0){
            long res = 2;
            for (int i = 0; i < 32; i++) {
                if (((x >> i) & 1)==0) {
                    res*=(long)2;
                }
                if(x==Integer.MAX_VALUE) System.out.println(res/2);
                else System.out.println(res);
            }
        }
    }

    //题目2:包粽子, 四个数n, m, c0, d0, 一共n 克面粉， m种馅料
    //然后m行,每行四个数ai, bi, ci, di, ai 表示一共多少克该种馅料
    //每个粽子包法, bi克第i种馅料 + ci 克面粉, 收益di, 或者 c0 克面粉, 不包馅料, 收益d0
    //求最大收益
    static int maxValue = Integer.MIN_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int c0 = sc.nextInt();
        int d0 = sc.nextInt();
        sc.nextLine();
        int[][] nums = new int[m][4];
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < m; j++) {
                nums[i][j] = sc.nextInt();
                list.add(nums[i][j]);
            }
            lists.add(list);
        }
        sc.nextLine();
        getMax(lists, n, 0, c0, d0);
        System.out.println(maxValue);
    }

    /**
     *
     * @param lists
     * @param restMian  剩余面粉数量
     * @param money     当前积累价值
     * @param c0        消耗c0面粉
     * @param d0        产生d0价值
     */
    private static void getMax(ArrayList<ArrayList<Integer>> lists, int restMian, int money, int c0, int d0) {
        //判断当前lists里面还有没有，如果没有了，则我计算剩余面粉的价值加上后和现有的最大值比较
        if(lists.isEmpty()){
            int curMax= money+(restMian/c0)*d0;
            if(maxValue<curMax){
                maxValue=curMax;}
        }
        //如果还有，则我们还得按照方案继续分配
        ArrayList<Integer> temp;
        int tempMoney;
        for (int i = 0; i < lists.size(); i++) {
            temp=new ArrayList<>();
            //本种类做0次到可做的最大次
            tempMoney=money;
            for(int j=0;j<=lists.get(i).get(0)/lists.get(i).get(1);j++){
                //需要判断当前来做面粉够不够,只有够了，才能操作
                tempMoney=money;
                int te=restMian-lists.get(i).get(2)*j;
                if(te>0){
                    tempMoney+=lists.get(i).get(3)*j;
                    temp=lists.get(i);
                    lists.remove(lists.get(i));
                    getMax(lists,te,tempMoney,c0,d0);
                    lists.add(0,temp);
                }
            }
        }
    }
}
