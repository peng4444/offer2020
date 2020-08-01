package cn.offer2020.pbj.demo.leetcode.twopoint;

import java.util.Arrays;
import java.util.Stack;

/**
 * @pClassName: Demo57
 * @author: pengbingjiang
 * @create: 2020/8/1 19:22
 * @description: TODO
 */
public class Demo57 {

    public int[][] insert1(int[][] intervals, int[] newInterval) {
        int ans[][]=new int[intervals.length+1][2];
        System.arraycopy(intervals,0,ans,0,intervals.length);
        ans[intervals.length]=newInterval;
        Arrays.sort(ans,(a,b)->a[0]-b[0]);
        int zhizhen=0;
        for(int i=1;i<=intervals.length;i++){
            if(ans[i][0]<=ans[zhizhen][1]){
                ans[zhizhen][1]=Math.max(ans[zhizhen][1],ans[i][1]);
            }
            else{
                zhizhen++;
                ans[zhizhen][0]=ans[i][0];
                ans[zhizhen][1]=ans[i][1];
            }
        }
        return Arrays.copyOf(ans,zhizhen+1);
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] newIntervals = new int[intervals.length + 1][];
        System.arraycopy(intervals, 0, newIntervals, 0, intervals.length);
        newIntervals[intervals.length] = newInterval;

        Arrays.sort(newIntervals, (a, b) -> a[0] - b[0]);
        Stack<int[]> stack = new Stack<>();
        for (int[] num : newIntervals) {
            if (stack.isEmpty()) {
                stack.push(num);
                continue;
            }
            int[] arr = stack.peek();
            if (arr[1] >= num[0]) {
                int[] combine = {arr[0], Math.max(arr[1], num[1])};
                stack.pop();
                stack.push(combine);
            } else {
                stack.push(num);
            }
        }
        return stack.toArray(new int[0][]);
    }
}
