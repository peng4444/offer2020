package cn.offer2020.pbj.demo.leetcode.queue_stack;

import java.util.Stack;

/**
 * @ClassName: Demo232
 * @Author: pbj
 * @Date: 2019/12/12 10:31
 * @Description: TODO 232用栈实现队列
 */
//栈的顺序为后进先出，而队列的顺序为先进先出。使用两个栈实现队列，一个元素需要经过两个栈才能出队列，在经
//过第一个栈时元素顺序被反转，经过第二个栈时再次被反转，此时就是先进先出顺序。
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
