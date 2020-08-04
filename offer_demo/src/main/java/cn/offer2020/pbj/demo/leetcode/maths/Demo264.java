package cn.offer2020.pbj.demo.leetcode.maths;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @ClassName: Demo264
 * @Author: pbj
 * @Date: 2020/4/15 11:54
 * @Description: TODO 264.丑数II
 */
public class Demo264 {
    //三指针，第一个丑数是1，以后的丑数都是基于前面的小丑数分别乘2，3，5构成的。
    // 我们每次添加进去一个当前计算出来个三个丑数的最小的一个，并且是谁计算的，谁指针就后移一位。
    public int nthUglyNumber(int n) {
        int num2 = 0,num3 = 0,num5 = 0;
        int[] ans = new int[n];
        ans[0] = 1;
        for(int i = 1;i<n;i++){
            ans[i] = Math.min(ans[num2]*2,Math.min(ans[num3]*3,ans[num5]*5));
            if(ans[i] == ans[num2]*2) num2++;
            if(ans[i] == ans[num3]*3) num3++;
            if(ans[i] == ans[num5]*5) num5++;
        }
        return ans[n-1];
    }
    //堆
    class Ugly {
        public int[] nums = new int[1690];
        Ugly() {
            HashSet<Long> seen = new HashSet();
            PriorityQueue<Long> heap = new PriorityQueue<Long>();
            seen.add(1L);
            heap.add(1L);

            long currUgly, newUgly;
            int[] primes = new int[]{2, 3, 5};
            for(int i = 0; i < 1690; ++i) {
                currUgly = heap.poll();
                nums[i] = (int)currUgly;
                for(int j : primes) {
                    newUgly = currUgly * j;
                    if (!seen.contains(newUgly)) {
                        seen.add(newUgly);
                        heap.add(newUgly);
                    }
                }
            }
        }
    }

    // 动态规划
    class Solution {
        Ugly u = new Ugly();
        public int nthUglyNumber(int n) {
            return u.nums[n - 1];
        }
    }

    class Ugly1 {
        public int[] nums = new int[1690];
        Ugly1() {
            nums[0] = 1;
            int ugly, i2 = 0, i3 = 0, i5 = 0;

            for(int i = 1; i < 1690; ++i) {
                ugly = Math.min(Math.min(nums[i2] * 2, nums[i3] * 3), nums[i5] * 5);
                nums[i] = ugly;

                if (ugly == nums[i2] * 2) ++i2;
                if (ugly == nums[i3] * 3) ++i3;
                if (ugly == nums[i5] * 5) ++i5;
            }
        }
    }

    class Solution1 {
        Ugly1 u = new Ugly1();
        public int nthUglyNumber(int n) {
            return u.nums[n - 1];
        }
    }

}
