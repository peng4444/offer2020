package cn.offer2020.pbj.demo.leetcode.maths;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @ClassName: Demo264
 * @Author: pbj
 * @Date: 2020/4/15 11:54
 * @Description: TODO 264. 丑数 II
 */
public class Demo264 {
    public int nthUglyNumber(int n) {
        int[] num = new int[n];
        num[0] = 1;
        int index2 = 0,index3 = 0,index5 = 0;
        int[] result=new int[n];
        result[0]=1;

        int begin = 1;
        while(begin<n){
            result[begin]=Math.min(result[index2]*2,Math.min(result[index3]*3,result[index5]*5));
            if(result[begin]==result[index2]*2) index2++;
            if(result[begin]==result[index3]*3) index3++;
            if(result[begin]==result[index5]*5) index5++;
            begin++;
        }
        return result[--begin];
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
