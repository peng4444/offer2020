package cn.offer2020.pbj.demo.leetcode.binarySearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @pClassName: Demo658
 * @author: pengbingjiang
 * @create: 2020/7/9 14:41
 * @description: TODO 658. 找到K个最接近的元素
 */
public class Demo658 {
    //二分查找
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        int left = 0,right = arr.length - k;
        while (left < right) {
            int mid = left + (right - left)/2;
            if (Math.abs(arr[mid] - x) > Math.abs(mid + k) - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        for (int i = left; i < left + k; i++) {
            list.add(arr[i]);
        }
        Set<Integer> set = new HashSet<>();

        return list;
    }

    //滑动窗口
    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
        int[] nums = new int[arr.length];//借助一个额外的数组保存每一个元素与x的差值的绝对值
        for (int i = 0; i < arr.length; i++) {
            nums[i] = Math.abs(arr[i] - x);
        }
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];//先保存初始窗口内的k个差值的和
        }
        int minDist = sum,start = 0;
        for (int i = 0; i < nums.length-k; i++) {
            sum += nums[i + k] - nums[i];
            if (sum < minDist) {
                start = i + 1;
                minDist = sum;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(arr[i + start]);
        }
        return ans;
    }
}
