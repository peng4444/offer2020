package cn.offer2020.pbj.demo.leetcode;

import java.util.Stack;

/**
 * @ClassName: Demo232
 * @Author: pbj
 * @Date: 2019/12/12 10:31
 * @Description: TODO 用栈实现队列
 */
public class Demo232 {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    public Demo232() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (stack1.isEmpty()&&stack2.isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        } else if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (stack1.isEmpty()&&stack2.isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        } else if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        if (stack1.isEmpty()&& stack2.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
