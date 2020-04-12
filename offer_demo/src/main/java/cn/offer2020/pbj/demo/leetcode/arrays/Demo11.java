package cn.offer2020.pbj.demo.leetcode.arrays;

/**
 * @ClassName: Demo11
 * @Author: pbj
 * @Date: 2020/3/23 10:48
 * @Description: TODO
 */
public class Demo11 {

    //暴力法 考虑每对可能出现的线段组合并找出这些情况之下的最大面积
    public int maxArea(int[] height){
        int maxarea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxarea = Math.max(maxarea, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return maxarea;
    }

    //双指针法
    public int maxArea2(int[] height){
        int maxArea = 0,left = 0,right=height.length-1;
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
