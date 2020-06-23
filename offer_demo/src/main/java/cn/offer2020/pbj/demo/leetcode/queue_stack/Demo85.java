package cn.offer2020.pbj.demo.leetcode.queue_stack;

import java.util.Stack;

public class Demo85 {
    public int maximalRectangle1(char[][] matrix) {
        if(matrix.length==0||matrix[0].length==0) return 0;
        int col = matrix.length;
        int row = matrix[0].length;
        int[] heights = new int[row];
        int ans = 0;
        for(int i = 0;i<col;i++){
            for(int j = 0;j<row;j++){
                if(matrix[i][j]=='1'){
                    heights[j]+=1;
                }else{
                    heights[j] = 0;
                }
            }
            ans = Math.max(ans,largestRectangleArea(heights));
        }
        return ans;
    }
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

    //动态规划
    public int maximalRectangle(char[][] matrix) {

        if (matrix.length == 0) return 0;
        int maxarea = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == '1'){

                    // compute the maximum width and update dp with it
                    dp[i][j] = j == 0? 1 : dp[i][j-1] + 1;

                    int width = dp[i][j];

                    // compute the maximum area rectangle with a lower right corner at [i, j]
                    for(int k = i; k >= 0; k--){
                        width = Math.min(width, dp[k][j]);
                        maxarea = Math.max(maxarea, width * (i - k + 1));
                    }
                }
            }
        } return maxarea;
    }
}
