package cn.offer2020.pbj.demo.leetcode.queue_stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @ClassName: Demo503
 * @Author: pbj
 * @Date: 2020/1/14 11:42
 * @Description: TODO 503.下一个更大元素 II
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
 * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
 * 如果不存在，则输出 -1。
 */
public class Demo503 {

    public int[] nexGreaterElements(int[] nums) {
        int n = nums.length;
        int[] next = new int[n];
        Arrays.fill(next,-1);
        Stack<Integer> pre = new Stack<>();
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!pre.isEmpty() && nums[pre.peek()] < num) {
                next[pre.pop()]=num;
            }
            if (i < n) {
                pre.push(i);
            }
        }
        return next;
    }

    public int[] nextGreaterElements1(int[] nums) {
        int[] a=new int[2*nums.length];
        int[] b=new int[nums.length];
        Arrays.fill(b,-1);
        for(int i=0;i<nums.length;i++){
            a[i]=nums[i];
            a[nums.length+i]=nums[i];
        }
        for(int i=0;i<nums.length;i++){
            for(int j=i;j<a.length;j++){
                if(a[j]>a[i]){
                    b[i]=a[j];
                    break;
                }
            }
        }
        return b;
    }
}
