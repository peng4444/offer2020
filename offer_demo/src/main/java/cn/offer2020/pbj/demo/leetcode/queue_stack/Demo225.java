package cn.offer2020.pbj.demo.leetcode.queue_stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName: Demo225
 * @Author: pbj
 * @Date: 2019/12/12 11:05
 * @Description: TODO 225.用队列实现栈
 */
//在将一个元素 x 插入队列时，为了维护原来的后进先出顺序，需要让 x 插入队列首部。而队列的默认插入顺序是队列
//尾部，因此在将 x 插入队列尾部之后，需要让除了 x 之外的所有元素出队列，再入队列。
public class Demo225 {
    private Queue<Integer> queue1;
    int top;
    /** Initialize your data structure here. */
    public Demo225() {
        queue1 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        top = x;
        queue1.offer(top);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        Queue<Integer> temp = new LinkedList<>();
        int oldTop = top;
        int size = queue1.size();
        for (int i = 0; i < size - 1; i++) {
            top = (int)queue1.remove();
            temp.offer(top);
        }
        queue1 = temp;
        return oldTop;
    }

    /** Get the top element. */
    public int top() {
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        if (queue1.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
