package cn.offer2020.pbj.demo.leetcode.queue_stack;

import java.util.Stack;
/**
* @Description: 84. 柱状图中最大的矩形
* @Param: 
* @return: 
* @Author: pengbingjiang
* @Date: 2020/7/1 12:10
*/
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

    //暴力求解3
    public int largestRectangleArea3(int[] heights) {
        int len = heights.length;
        int max = 0;
        for(int i = 0;i<len;i++){
            int min = heights[i];
            for(int j = i;j<heights.length;j++){
                if(min>heights[j]) min = heights[j];
                int temp = min*(j-i+1);
                if(temp>max) max = temp;
            }
        }
        return max;
    }

    //暴力求解1
    public int largestRectangleArea2(int[] heights) {
        int len = heights.length;
        int ans = 0;
        // 枚举左边界
        for(int i = 0;i<len;i++){
            int minHeight = Integer.MAX_VALUE;
            // 枚举右边界
            for(int j = i;j<heights.length;j++){
                // 确定高度
                minHeight = Math.min(minHeight, heights[j]);
                // 计算面积
                ans = Math.max(ans, (j - i + 1) * minHeight);
            }
        }
        return ans;
    }

    //暴力求解2
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
