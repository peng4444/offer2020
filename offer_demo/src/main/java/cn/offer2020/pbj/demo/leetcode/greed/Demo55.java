package cn.offer2020.pbj.demo.leetcode.greed;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName: Demo55
 * @Author: pbj
 * @Date: 2020/4/24 14:34
 * @Description: TODO 55. 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 */
public class Demo55 {
    /* *
     * 功能描述: 从后往前遍历数组，如果遇到的元素可以到达最后一行，则截断后边的元素。否则继续往前，弱最后剩下的元素大于1个，
     * 则可以判断为假。否则就是真，时间复杂度O(n)就可以，不知道有没有大佬可以继续优化。
     * @param: [nums]
     * @return: boolean
     * @auther: pbj
     * @date: 2020/4/24 14:35
     */
    public boolean canJump(int[] nums) {
        int n = 1;
        for(int i = nums.length-2;i>=0;i--){
            if(nums[i]>=n){
                n = 1;
            }else{
                n++;
            }
            if(i==0&&n>1){
                return false;
            }
        }
        return true;
    }

    //贪心
    public boolean canJump1(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    //本质上是隐式(implict)BFS，我们维护一个最远能走到的距离rightmost，在维护该变量的过程中发现它的距离大于等于nums.length-1，
    //那就能够到达末尾。如果看不太懂，放一个显式(explicit)BFS，能通过，只不过非常慢。
    public boolean canJump2(int[] nums) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        q.offer(0);
        while(!q.isEmpty()) {
            for(int size=q.size();size>0;size--) {
                // idx is current position
                int idx = q.remove();
                // if this condition is true, we can find an answer
                if(idx >= nums.length - 1) return true;
                // i is the next rightmost position we can reach
                for(int i=idx + 1;i < nums.length &&
                        i<=idx + nums[idx];i++) {
                    if(visited[i]) continue;
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }
        return false;
    }
}
