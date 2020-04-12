package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo376
 * @Author: pbj
 * @Date: 2020/4/12 11:50
 * @Description: TODO 376. 摆动序列
 */
public class Demo376 {
    //贪心
    public class Solution5 {
        public int wiggleMaxLength(int[] nums) {
            if (nums.length < 2)
                return nums.length;
            int prevdiff = nums[1] - nums[0];
            int count = prevdiff != 0 ? 2 : 1;
            for (int i = 2; i < nums.length; i++) {
                int diff = nums[i] - nums[i - 1];
                if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
                    count++;
                    prevdiff = diff;
                }
            }
            return count;
        }
    }

    // 空间优化的动态规划
    public class Solution4 {
        public int wiggleMaxLength(int[] nums) {
            if (nums.length < 2)
                return nums.length;
            int down = 1, up = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > nums[i - 1])
                    up = down + 1;
                else if (nums[i] < nums[i - 1])
                    down = up + 1;
            }
            return Math.max(down, up);
        }
    }

    //线性动态规划
    public class Solution2 {
        public int wiggleMaxLength(int[] nums) {
            if (nums.length < 2)
                return nums.length;
            int[] up = new int[nums.length];
            int[] down = new int[nums.length];
            up[0] = down[0] = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > nums[i - 1]) {
                    up[i] = down[i - 1] + 1;
                    down[i] = down[i - 1];
                } else if (nums[i] < nums[i - 1]) {
                    down[i] = up[i - 1] + 1;
                    up[i] = up[i - 1];
                } else {
                    down[i] = down[i - 1];
                    up[i] = up[i - 1];
                }
            }
            return Math.max(down[nums.length - 1], up[nums.length - 1]);
        }
    }

    //动态规划
    public class Solution1 {
        public int wiggleMaxLength(int[] nums) {
            if (nums.length < 2)
                return nums.length;
            int[] up = new int[nums.length];
            int[] down = new int[nums.length];
            for (int i = 1; i < nums.length; i++) {
                for(int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        up[i] = Math.max(up[i],down[j] + 1);
                    } else if (nums[i] < nums[j]) {
                        down[i] = Math.max(down[i],up[j] + 1);
                    }
                }
            }
            return 1 + Math.max(down[nums.length - 1], up[nums.length - 1]);
        }
    }

    //暴力
    public class Solution {
        private int calculate(int[] nums, int index, boolean isUp) {
            int maxcount = 0;
            for (int i = index + 1; i < nums.length; i++) {
                if ((isUp && nums[i] > nums[index]) || (!isUp && nums[i] < nums[index]))
                    maxcount = Math.max(maxcount, 1 + calculate(nums, i, !isUp));
            }
            return maxcount;
        }

        public int wiggleMaxLength(int[] nums) {
            if (nums.length < 2)
                return nums.length;
            return 1 + Math.max(calculate(nums, 0, true), calculate(nums, 0, false));
        }
    }
}
