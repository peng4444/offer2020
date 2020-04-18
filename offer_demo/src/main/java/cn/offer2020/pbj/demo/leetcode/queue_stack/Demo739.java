package cn.offer2020.pbj.demo.leetcode.queue_stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @ClassName: Demo739
 * @Author: pbj
 * @Date: 2020/1/14 11:34
 * @Description: TODO 739.每日温度 根据每日 气温 列表，请重新生成一个列表，
 * 对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 */
public class Demo739 {
    //在遍历数组时用栈把数组中的数存起来，如果当前遍历的数比栈顶元素来的大，说明栈顶元素的下一个比它大的数就是当前元素。
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] dist = new int[n];
        Stack<Integer> indexs = new Stack<>();
        for (int curIndex = 0; curIndex < n; curIndex++) {
            while (!indexs.isEmpty() && temperatures[curIndex] > temperatures[indexs.peek()]) {
                int perIndex = indexs.pop();
                dist[perIndex] = curIndex - perIndex;
            }
            indexs.add(curIndex);
        }
        return dist;
    }

    class Solution {
        public int[] dailyTemperatures(int[] T) {
            int[] ans = new int[T.length];
            int[] next = new int[101];
            Arrays.fill(next, Integer.MAX_VALUE);
            for (int i = T.length - 1; i >= 0; --i) {
                int warmer_index = Integer.MAX_VALUE;
                for (int t = T[i] + 1; t <= 100; ++t) {
                    if (next[t] < warmer_index)
                        warmer_index = next[t];
                }
                if (warmer_index < Integer.MAX_VALUE)
                    ans[i] = warmer_index - i;
                next[T[i]] = i;
            }
            return ans;
        }
    }
}
