package cn.offer2020.pbj.demo.leetcode.arrays;

/**
 * @ClassName: Demo11
 * @Author: pbj
 * @Date: 2020/3/23 10:48
 * @Description: TODO  11. 盛最多水的容器
 */
public class Demo11 {

    //暴力法 考虑每对可能出现的线段组合并找出这些情况之下的最大面积
    public int maxArea(int[] height){
        int maxarea = 0;
        for (int i = 0; i < height.length-1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = Math.min(height[i], height[j]) * (j - i);
                maxarea = Math.max(maxarea, area);
            }
        }
        return maxarea;
    }

    //双指针法 左右边界向中间缩，两边比较短的那个收缩。
    public int maxArea2(int[] height){
        int maxArea = 0,left = 0,right=height.length-1;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea,area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
    //还是最快的
    public int maxArea3(int[] height){
        int max = 0;
        for (int i = 0, j = height.length - 1; i < j;) {
            int minHeight = height[i] < height[j] ? height[i++] : height[j--];
            int area = (j - i + 1) * minHeight;
            max = Math.max(max, area);
        }
        return max;
    }
}
