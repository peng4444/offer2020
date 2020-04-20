package cn.offer2020.pbj.demo.leetcode;

import java.util.*;

/**
 * @ClassName: Demo719
 * @Author: pbj
 * @Date: 2020/4/20 16:11
 * @Description: TODO 719. 找出第 k 小的距离对
 * 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 */
public class Demo719 {
    //二分查找 + 双指针
    public int smallestDistancePair2(int[] nums, int k) {
        Arrays.sort(nums);

        int lo = 0;
        int hi = nums[nums.length - 1] - nums[0];
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            int count = 0, left = 0;
            for (int right = 0; right < nums.length; ++right) {
                while (nums[right] - nums[left] > mi) left++;
                count += right - left;
            }
            //count = number of pairs with distance <= mi
            if (count >= k) hi = mi;
            else lo = mi + 1;
        }
        return lo;
    }

    //二分查找 + 前缀和
    public int smallestDistancePair1(int[] nums, int k) {
        Arrays.sort(nums);
        int WIDTH = 2 * nums[nums.length - 1];

        //multiplicity[i] = number of nums[j] == nums[i] (j < i)
        int[] multiplicity = new int[nums.length];
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == nums[i - 1]) {
                multiplicity[i] = 1 + multiplicity[i - 1];
            }
        }

        //prefix[v] = number of values <= v
        int[] prefix = new int[WIDTH];
        int left = 0;
        for (int i = 0; i < WIDTH; ++i) {
            while (left < nums.length && nums[left] == i) left++;
            prefix[i] = left;
        }

        int lo = 0;
        int hi = nums[nums.length - 1] - nums[0];
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            int count = 0;
            for (int i = 0; i < nums.length; ++i) {
                count += prefix[nums[i] + mi] - prefix[nums[i]] + multiplicity[i];
            }
            //count = number of pairs with distance <= mi
            if (count >= k) hi = mi;
            else lo = mi + 1;
        }
        return lo;
    }

    /* *
     * 功能描述: 自己写的：1.用HashSet保存  重复的相等值会覆盖 只能通过部分
     *                    2.使用List保存，不做重复判断  能够通过大部分  会超出内存限制
     * @param: [nums, k]
     * @return: int
     * @auther: pbj
     * @date: 2020/4/20 16:11
     */
    public static int smallestDistancePair(int[] nums, int k) {
        List<Integer> set = new ArrayList<>();
        for(int i = 0;i<nums.length-1;i++){
            for(int j = i+1;j<nums.length;j++){
                set.add(Math.abs(nums[i]-nums[j]));
            }
        }
        int n = 1;
        Collections.sort(set);
        for (int i = 0; i < set.size(); i++) {
            if(k==i+1){
                return set.get(i);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(smallestDistancePair(new int[]{1,6,1},3));
    }
}
