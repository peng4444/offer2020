package cn.offer2020.pbj.demo.job.alibaba;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @pClassName: ALibaba
 * @author: pengbingjiang
 * @create: 2020/7/25 09:09
 * @description: TODO
 */
public class ALibaba0724 {
    /*
    *
    1. 吃烧饼，有n个盘子和每个盘子的烧饼数，每次选一个x(x <= n)，吃掉第1~x号盘子的一个烧饼，
    * 若第1~x号盘子中有空盘，则不能选择这个x。 假设胃无限大，问最多可以吃多少烧饼。
    样例：
    输入
        3
        2 1 3
    输出：
        4
        这题O(n),记录一个当前最小值即可。
    */
    public static void main1(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int N = sc.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }
        long res = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if(nums[i]<min){
                min = nums[i];
            }
            res = res + min;
        }
        System.out.println(res);
    }
    /*
    *
        2. N行L列的灯，有L个开关，第i个可以控制第i列，使得该列灯状态反转（开变关，关变开）。行之间可以任意交换。
        * 问给定初始灯状态和目标灯状态，能否从初始变到目标状态，能的话最少要按几次开关。
        这题我一开始题目没看清，以为只能开始的时候行互换，做完才发现错了，可是后来时间紧张就更加没思路了，
        * 求问大佬怎么做啊
        * 这题可以用二进制来做，对于每行用一个\(long \ long\)来存，我们枚举原本状态的第一行与目标状态的第几行匹配，异或代表这两行的数，得到一个数\(c\)，每\(i\)位为1就表示第\(i\)列要按，否则不按。然后把原本状态的每行异或一下这个数，这样就可以求出按后的状态，再把按后的与目标的进行匹配，匹配成功且按的次数少的就更新答案，注意要把按后的状态再按回来。时间复杂度\(O(n^3)\)。
        * [开关灯](https://blog.csdn.net/weixin_43346722/article/details/106909661)
    */
    long T,n,l,ans;
    long[] a = new long[151];
    long[] b = new long[151];
    boolean[] in = new boolean[151];
    char c;
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int size = sc.nextInt();
        for (int i = 1; i <= size; i++) {

        }
    }
}
