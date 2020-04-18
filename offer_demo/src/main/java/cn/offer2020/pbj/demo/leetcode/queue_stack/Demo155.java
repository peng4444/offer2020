package cn.offer2020.pbj.demo.leetcode.queue_stack;

import java.util.Stack;

/**
 * @ClassName: Demo155
 * @Author: pbj
 * @Date: 2020/1/14 11:31
 * @Description: TODO 115.最小栈 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 */
public class Demo155 {
    //对于实现最小值队列问题，可以先将队列使用栈来实现，然后就将问题转换为最小值栈
    public class MinStack {

        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;
        private int min;

        public MinStack() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            dataStack.add(x);
            min = Math.min(min, x);
            minStack.add(min);
        }

        public void pop() {
            dataStack.pop();
            minStack.pop();
            min = minStack.isEmpty() ? Integer.MAX_VALUE : minStack.peek();
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

}
