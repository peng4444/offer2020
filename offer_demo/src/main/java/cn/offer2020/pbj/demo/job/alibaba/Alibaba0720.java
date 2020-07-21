package cn.offer2020.pbj.demo.job.alibaba;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @pClassName: Alibaba0720
 * @author: pengbingjiang
 * @create: 2020/7/21 15:22
 * @description: TODO [阿里7.20机考题](https://www.nowcoder.com/discuss/455801)
 * (https://www.pasteme.cn/44096)
 */
public class Alibaba0720 {

    /**
    * @Description:
     * 1. 给定N和K，求互不相同的正整数x,y,z使得x+y+z=N，且gcd(x,y)=gcd(x,z)=gcd(y,z)=K。
     * 条件：1 ≤N, K≤ 1e18
     * 思路：等式两边除K，得到x'+y'+z'=N'=N/K，且x',y',z'两两互素。
     * 当N'为偶数，直接构造x'=1, y'=N'/2, z' = y'-1满足条件。
     * 当N'为奇数，另x'=1,则y'+z'=N'-1。由于N'-1为偶数且y'和z'互素，必然有y',z'都为奇数。令y'=3,5,..., N' / 2逐个搜索即可。
    * @Param: [args]
    * @return: void
    * @Author: pengbingjiang
    * @Date: 2020/7/21 15:24
    */
    public static void main1(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int size = sc.nextInt();
        for (int i = 0; i < size; i++) {
            long n = sc.nextInt();
            long k = sc.nextInt();
            if (n % k != 0) {
                System.out.println("-1");
            } else {
                long temp = n / k;
                if (temp < 6) {
                    System.out.println("-1");
                } else {
                    if ((temp - 1) % 2 == 1) {
                        System.out.println(k + " " + k * 2 + " " + k * (temp - 3));
                    } else {
                        long mid = (temp-1)/2;
                        long left,right;
                        if (mid % 2 == 1) {
                            left = mid - 2;
                            right = mid + 2;
                        } else {
                            left = mid - 1;
                            right = mid + 1;
                        }
                        if (left <= 1) {
                            System.out.println("-1");
                        } else {
                            System.out.println(k + " " + left * k + " " + right * k);
                        }
                    }
                }
            }
        }
    }

    /**
    * @Description:
    2.求区间[l, r]内的幸运数。幸运数定义为，将相邻数位差的绝对值拼成下一个数，重复该操作直到只剩1位。
    剩下7的是幸运数。例如，219->18->7或者118->7
    条件：1 ≤ l ≤ r ≤ 1e9
    直接举例，以458为例，458因|4-5|=1得出新数18，又一次计算得出|1-8|=7最后算出7，则为幸运数，若为别的则非幸运数。
    思路：根据1,...,k-1位的幸运数，给定首位数字，可以搜索得到k位的幸运数。将所有幸运数排序后进行二分查找。
    * @Param: [args]
    * @return: void
    * @Author: pengbingjiang
    * @Date: 2020/7/21 15:39
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int size = sc.nextInt();
        for (int i = 0; i < size; i++) {
            int left = sc.nextInt();
            int right = sc.nextInt();
            int ans = 0;
            for (int num = left; num <= right; num++) {
                if(help(num)){
                    ans++;
                    System.out.print(num+" ");
                }
            }
            System.out.println("幸运数的个数："+ans);
        }
    }

    private static boolean help(int num) {
        int[] a = new int[15];
        while (true) {
            int temp = num;
            if (num >= 0 && num < 10) {
                if(num==7) return true;
                else return false;
            }
            int count = 0;
            //将整数按位存入数组
            while (temp > 0) {
                a[count] = temp % 10;
                count++;
                temp = temp/10;
            }
            num = 0;
            for (int i = count - 1; i >= 1; i--) {
                num = num * 10 + Math.abs(a[i] - a[i - 1]);
            }
        }
    }
}
