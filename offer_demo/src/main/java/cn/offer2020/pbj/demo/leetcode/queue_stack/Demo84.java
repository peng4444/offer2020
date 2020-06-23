package cn.offer2020.pbj.demo.leetcode.queue_stack;

import java.util.Stack;

public class Demo84 {
    //单调栈求解
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i<=len;i++){
            //一旦当前高度比堆栈顶端的高度要矮即可对堆栈顶端高度进行计算面积
            while(!stack.isEmpty()&&(i==len||heights[i]<heights[stack.peek()])){
                int height = heights[stack.pop()];
                int width = stack.isEmpty()?i:i-1-stack.peek();
                max = Math.max(max,width*height);
            }
            //当前高度比堆栈顶端高度要高，压入堆栈
            stack.push(i);
        }
        return max;
    }

    //暴力求解
    public int largestRectangleArea1(int[] heights) {
        int area = 0, n = heights.length;
        // 遍历每个柱子，以当前柱子的高度作为矩形的高 h，
        // 从当前柱子向左右遍历，找到矩形的宽度 w。
        for (int i = 0; i < n; i++) {
            int w = 1, h = heights[i], j = i;
            while (--j >= 0 && heights[j] >= h) {
                w++;
            }
            j = i;
            while (++j < n && heights[j] >= h) {
                w++;
            }
            area = Math.max(area, w * h);
        }

        return area;
    }
}
