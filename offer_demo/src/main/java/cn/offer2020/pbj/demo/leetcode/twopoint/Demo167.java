package cn.offer2020.pbj.demo.leetcode.twopoint;

import java.util.HashMap;

/**
 * @ClassName: Demo167
 * @Author: pbj
 * @Date: 2020/4/5 11:36
 * @Description: TODO 167.两数之和 II - 输入有序数组
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 */
public class Demo167 {

    /* *
     * 功能描述: 使用双指针二分查找，一个指针指向值较小的元素，一个指针指向值较大的元素。
     * 指向较小元素的指针从头向尾遍历，指向较大元素的指针从尾向头遍历。
     * @param: [nums, target]
     * @return: int[]
     * @auther: pbj
     * @date: 2020/4/2 11:19
     */
    public int[] twoSum3(int[] nums, int target) {
        int i = 0,j=nums.length-1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                return new int[]{i+1,j+1};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

    public int[] twoSum(int[] numbers, int target) {
        //HashMap
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<numbers.length;i++){
            if(!map.containsKey(target-numbers[i])){
                map.put(numbers[i],i);
            }else{
                return new int[]{map.get(target-numbers[i])+1,i+1};
            }
        }
        return new int[]{};
    }
}
