package cn.offer2020.pbj.demo.leetcode.string;

import java.util.*;

/**
 * @ClassName: Demo128
 * @Author: pbj
 * @Date: 2020/1/4 13:25
 * @Description: TODO 128.最长连续序列
 * 给定一个未排序的整数数组，找出最长连续序列的长度。要求算法的时间复杂度为 O(n)。
 */
public class Demo128 {

    /* *
     * 功能描述: 采用哈希表存储数组中所有元素，然后应用哈希表查询当前元素的左右两边序列数字是否存在，查询操作的时间复杂度为O(1)，所以整体的时间复杂度为O(n)。
     * @param: [nums]
     * @return: int
     * @auther: pbj
     * @date: 2020/1/4 13:26
     */
    public int longestConsecutive(int[] nums) {
        int result = 0;
        Set<Integer> set = new HashSet<>();
        for (Integer num : nums) {
            set.add(num);
        }
        for (Integer num : nums) {
            if (set.contains(num)) {
                int len = 1;
                int temp = num;
                while (set.contains(--temp)) {
                    len++;
                    set.remove(temp);
                }
                temp = num;
                while (set.contains(++temp)) {
                    len++;
                    set.remove(temp);
                }
                result = Math.max(result, len);
            }
        }
        return result;
    }

    public int longestConsecutive1(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        Map<Integer,Integer> map = new HashMap<>(len);
        int result = 1;
        for(int i = 0;i<len;i++){
            int cur = nums[i];
            // 某元素已经出现过则跳过不用统计
            if(!map.containsKey(cur)){
                int leftLen = map.getOrDefault(cur-1,0);
                int rightLen = map.getOrDefault(cur+1,0);
                int curLen = leftLen + 1 + rightLen;
                if(curLen > result){
                    result = curLen;
                }
                // 放入哈希表中，用来给后面的元素判断它们的值是否在前面出现过
                map.put(cur,-1);
                // 左右端点记录当前最长连续序列值即可，区间之间的元素不用记录，因为它们已经出现过并统计过了
                map.put(cur - leftLen,curLen);
                map.put(cur + rightLen,curLen);
            }
        }
        return result;
    }

    /* *
     * 功能描述:排序 如果我们可以将数组中的数字升序迭代，找连续数字会变得十分容易。为了将数组变得有序，我们将数组进行排序。
     * 时间复杂度O(nlogN)
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2020/1/4 13:35
     */
    class Solution {
        public int longestConsecutive(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            Arrays.sort(nums);
            int longestStreak = 1;
            int currentStreak = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[i-1]) {
                    if (nums[i] == nums[i-1]+1) {
                        currentStreak += 1;
                    }
                    else {
                        longestStreak = Math.max(longestStreak, currentStreak);
                        currentStreak = 1;
                    }
                }
            }
            return Math.max(longestStreak, currentStreak);
        }
    }
    /* *
     * 功能描述:暴力法 时间复杂度O(N^3)
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2020/1/4 13:34
     */


    class Solution2 {
        private boolean arrayContains(int[] arr, int num) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == num) {
                    return true;
                }
            }

            return false;
        }
        public int longestConsecutive(int[] nums) {
            int longestStreak = 0;

            for (int num : nums) {
                int currentNum = num;
                int currentStreak = 1;

                while (arrayContains(nums, currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }

            return longestStreak;
        }
    }
}
