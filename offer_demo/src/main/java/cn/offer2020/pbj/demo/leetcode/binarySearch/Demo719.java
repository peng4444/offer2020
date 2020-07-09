package cn.offer2020.pbj.demo.leetcode.binarySearch;

import java.util.*;

/**
 * @pClassName: Demo719
 * @author: pengbingjiang
 * @create: 2020/7/9 15:21
 * @description: TODO 719. 找出第k小的距离对
 * 给定一个整数数组，返回所有数对之间的第k个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 */
public class Demo719 {

    //二分查找+双指针
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = nums[nums.length - 1] - nums[0];
        while (left < right) {
            int mid = left + (right - left) /2;
            int count = 0,start = 0;
            for (int i = 0; i < nums.length; i++) {
                while (nums[i] - nums[start] > mid) {
                    start++;
                }
                count += i - start;
            }
            if (count >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }



    //将差值存入List集合 超时
    public int smallestDistancePair1(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i<nums.length-1;i++){
            for(int j = i+1;j<nums.length;j++){
                list.add(Math.abs(nums[j]-nums[i]));
            }
        }
        Collections.sort(list);
        if(k>list.size()) return -1;
        for (Integer s : list) {
            if(k==1){
                return s;
            }
            k--;
        }
        return -1;
    }
}
