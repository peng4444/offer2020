package cn.offer2020.pbj.demo.leetcode.queue_stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * @ClassName: Demo496
 * @Author: pbj
 * @Date: 2020/4/18 11:37
 * @Description: TODO 496. 下一个更大元素 I
 * 给定两个没有重复元素的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 */
public class Demo496 {
    /* *
     * 功能描述: 通过Stack、HashMap解决
        先遍历大数组nums2，首先将第一个元素入栈；
        继续遍历，当当前元素小于栈顶元素时，继续将它入栈；当当前元素大于栈顶元素时，栈顶元素出栈，此时应将该出栈的元素与当前元素形成key-value键值对，存入HashMap中；
        当遍历完nums2后，得到nums2中元素所对应的下一个更大元素的hash表；
        遍历nums1的元素在hashMap中去查找‘下一个更大元素’，当找不到时则为-1。
     * @param: [nums1, nums2]
     * @return: int[]
     * @auther: pbj
     * @date: 2020/4/18 11:38
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        int[] ans = new int[nums1.length];
        for(int num:nums2){
            while(!stack.isEmpty() && stack.peek()<num){
                map.put(stack.pop(),num);
            }
            stack.push(num);
        }
        for(int i = 0;i<nums1.length;i++){
            ans[i] =  map.getOrDefault(nums1[i],-1);
        }
        return ans;
    }

    //暴力遍历
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums2.length; i++) {
            for(int j = 0; j < nums1.length; j++) {
                if(nums2[i] == nums1[j]) {
                    result[j] = -1;
                    for (int k = i+1; k < nums2.length; k++) {
                        if(nums2[k] > nums1[j]) {
                            result[j] = nums2[k];
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }
}
