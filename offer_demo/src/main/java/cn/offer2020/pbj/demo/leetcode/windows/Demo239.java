package cn.offer2020.pbj.demo.leetcode.windows;

import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * @ClassName: Demo239
 * @Author: pbj
 * @Date: 2019/12/12 20:29
 * @Description: TODO 239.滑动窗口最大值
 * 给定一个数组nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的k个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 */
public class Demo239 {

    //思路： 遍历数组 L R 为滑窗左右边界 只增不减
    //        双向队列保存当前窗口中最大的值的数组下标 双向队列中的数从大到小排序，
    //        新进来的数如果大于等于队列中的数 则将这些数弹出 再添加
    //        当R-L+1=k 时 滑窗大小确定 每次R前进一步L也前进一步 保证此时滑窗中最大值的
    //        数组下标在[L，R]中，并将当前最大值记录
    public static int[] maxSlidingWindow0(int[] nums, int k) {
        int right =0;
        int[] res = new int[nums.length -k +1];
        int index=0;
        LinkedList<Integer> list = new LinkedList<>();
        // 开始构造窗口
        while (right < nums.length) {
            // 这里的list的首位必须是窗口中最大的那位
            while (!list.isEmpty() && nums[right] > list.peekLast()) {
                list.removeLast();
            }
            // 不断添加
            list.addLast(nums[right]);
            right++;
            // 构造窗口完成，这时候需要根据条件做一些操作
            if (right >= k){
                res[index++]=list.peekFirst();
                // 如果发现第一个已经在窗口外面了，就移除
                if(list.peekFirst() == nums[right-k]) {
                    list.removeFirst();
                }
            }
        }
        return res;
    }

    /* *
     * 功能描述: 动态规划
     * @param: [nums, k]
     * @return: int[]
     * @auther: pbj
     * @date: 2019/12/12 21:01
     */
    public int[] maxSlidingWindow4(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        int[] left = new int[n];
        left[0] = nums[0];
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            // from left to right
            if (i % k == 0) left[i] = nums[i];  // block_start
            else left[i] = Math.max(left[i - 1], nums[i]);

            // from right to left
            int j = n - i - 1;
            if ((j + 1) % k == 0) right[j] = nums[j];  // block_end
            else right[j] = Math.max(right[j + 1], nums[j]);
        }

        int[] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++)
            output[i] = Math.max(left[i + k - 1], right[i]);

        return output;
    }

    /* *
     * 功能描述: 双向队列 O(n)
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2019/12/12 20:51
     */
    ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
    int[] nums;

    public void clean_deque(int i, int k) {
        if (!queue.isEmpty() && queue.getFirst() == i - k) {
            queue.removeFirst();
        }
        while (!queue.isEmpty() && nums[i] > nums[queue.getLast()]) {
            queue.removeLast();
        }
    }

    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;
        this.nums = nums;
        int max_idx = 0;
        for (int i = 0; i < k; i++) {
            clean_deque(i, k);
            queue.addLast(i);
            if (nums[i] > nums[max_idx]) {
                max_idx = i;
            }
        }
        int[] output = new int[n - k + 1];
        output[0] = nums[max_idx];
        for (int i = k; i < n; i++) {
            clean_deque(i, k);
            queue.addLast(i);
            output[i - k + 1] = nums[queue.getFirst()];
        }
        return output;
    }

    /* *
     * 功能描述: 暴力法
     * 最简单直接的方法是遍历每个滑动窗口，找到每个窗口的最大值。一共有 N - k + 1 个滑动窗口，每个有 k 个元素，
     * 于是算法的时间复杂度为 O(Nk)，表现较差。
     * @param: [nums, k]
     * @return: int[]
     * @auther: pbj
     * @date: 2019/12/12 20:39
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        int[] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            output[i] = max;
        }
        return output;
    }

    /* *
     * 功能描述: 没看懂的解答
     * @param: [nums, k]
     * @return: int[]
     * @auther: pbj
     * @date: 2019/12/12 20:38
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k < 0) {
            throw new RuntimeException("nums or k not ok.");
        }
        int currMax = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            if (nums[i] > currMax) {
                currMax = nums[i];
            }
        }
        int[] ans = new int[nums.length - k + 1];
        ans[0] = currMax;
        for (int start = 1; start + k - 1 < nums.length; start++) {
            int end = start + k - 1;
            if (nums[end] > currMax) {
                currMax = nums[end];
            } else if (nums[start - 1] == currMax) {
                currMax = Integer.MIN_VALUE;
                for (int i = start; i <= end; i++) {
                    if (nums[i] > currMax) {
                        currMax = nums[i];
                    }
                }
            }
            ans[start] = currMax;
        }
        return ans;
    }
}
