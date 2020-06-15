package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.Arrays;
import java.util.Stack;

/**
 * @ClassName: Demo581
 * @Author: pbj
 * @Date: 2020/6/15 18:04
 * @Description: TODO 581. 最短无序连续子数组
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 */
public class Demo581 {
    public int findUnsortedSubarray(int[] nums) {
        //从左到右循环，记录最大值为 max，若 nums[i] < max, 则表明位置 i 需要调整, 循环结束，记录需要调整的最大位置 i 为 high; 同理，从右到左循环，记录最小值为 min, 若 nums[i] > min, 则表明位置 i 需要调整，循环结束，记录需要调整的最小位置 i 为 low.
        if(nums==null||nums.length<=1) return 0;
        int len = nums.length;
        int high = 0,low = len-1,max = nums[0],min = nums[len-1];
        for(int i = 1;i<len;i++){
            max = Math.max(max,nums[i]);
            min = Math.min(min,nums[len-1-i]);
            if(nums[i]<max) high = i;
            if(nums[len-1-i]>min) low = len-1-i;
        }
        return high>low?high-low+1:0;
    }

    //将数组 numsnums 进行排序，记为 nums\_sortednums_sorted 。然后我们比较 numsnums 和 nums\_sortednums_sorted 的元素来决定最左边和最右边不匹配的元素。它们之间的子数组就是要求的最短无序子数组。
    public int findUnsortedSubarray1(int[] nums) {
        int[] snums = nums.clone();
        Arrays.sort(snums);
        int start = snums.length, end = 0;
        for (int i = 0; i < snums.length; i++) {
            if (snums[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return (end - start >= 0 ? end - start + 1 : 0);
    }

    public int findUnsortedSubarray2(int[] nums) {
        Stack< Integer > stack = new Stack < Integer > ();
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                l = Math.min(l, stack.pop());
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                r = Math.max(r, stack.pop());
            stack.push(i);
        }
        return r - l > 0 ? r - l + 1 : 0;
    }
}
