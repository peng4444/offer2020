package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: Demo287
 * @Author: pbj
 * @Date: 2020/3/10 17:07
 * @Description: TODO
 */
public class Demo287 {

    //排序
    class Solution {
        public int findDuplicate(int[] nums) {
            Arrays.sort(nums);
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i-1]) {
                    return nums[i];
                }
            }

            return -1;
        }
    }

    //集合
    class Solution2 {
        public int findDuplicate(int[] nums) {
            Set<Integer> seen = new HashSet<Integer>();
            for (int num : nums) {
                if (seen.contains(num)) {
                    return num;
                }
                seen.add(num);
            }

            return -1;
        }
    }

    //二分查找解法
    public int findDuplicate(int[] nums) {
        int l = 1, h = nums.length - 1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            int cnt = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) cnt++;
            }
            if (cnt > mid) h = mid - 1;
            else l = mid + 1;
        }
        return l;
    }

    // 循环检测 双指针解法，类似于有环链表中找出环的入口:
    public int findDuplicate2(int[] nums) {
        int slow = nums[0], fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

}
