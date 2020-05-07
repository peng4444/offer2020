package cn.offer2020.pbj.demo.leetcode.maths;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: Demo18
 * @Author: pbj
 * @Date: 2020/5/7 10:40
 * @Description: TODO 18. 四数之和
 * 给定一个包含n个整数的数组nums和一个目标值target，判断nums中是否存在四个元素a，b，c和 d，
 * 使得a+b+c+d的值与target相等？找出所有满足条件且不重复的四元组。
 */
public class Demo18 {
    //使用双循环固定两个数，用双指针找另外两个数，通过比较与target 的大小，移动指针。
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if (nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j - i > 1 && nums[j] == nums[j - 1]) continue;
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if (nums[i] + nums[j] + nums[n - 1] + nums[n - 2] < target) continue;

                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    int tmp = nums[i] + nums[j] + nums[left] + nums[right];
                    if (tmp == target) {
                        List<Integer> tmpList = new LinkedList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        res.add(tmpList);
                        while (left < right && nums[left] == nums[left + 1]) left += 1;
                        while (left < right && nums[right] == nums[right - 1]) right -= 1;
                        left += 1;
                        right -= 1;
                    } else if (tmp > target) right -= 1;
                    else left += 1;
                }
            }
        }
        return res;
    }
}
