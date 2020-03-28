package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter5;

import java.util.Stack;

/**
 * @ClassName: StackToQueue
 * @Author: pbj
 * @Date: 2020/3/18 11:43
 * @Description: TODO
 */
public class StackQueue {
    private Stack<Integer> stackA = new Stack<Integer>();
    private Stack<Integer> stackB = new Stack<Integer>();

    /**
     * 4. * 入队操作5. * @param element 入队的元素6.
     */
    public void enQueue(int element) {
        stackA.push(element);
    }

    /**
     * 11. * 出队操作12.
     */
    public Integer deQueue() {
        if (stackB.isEmpty()) {
            if (stackA.isEmpty()) {
                return null;
            }
            transfer();
        }
        return stackB.pop();
    }

    /**
     * 24. * 栈A元素转移到栈B25.
     */
    private void transfer() {
        while (!stackA.isEmpty()) {
            stackB.push(stackA.pop());
        }
    }

    public static void main(String[] args) throws Exception {
        StackQueue stackQueue = new StackQueue();
        stackQueue.enQueue(1);
        stackQueue.enQueue(2);
        stackQueue.enQueue(3);
        System.out.println(stackQueue.deQueue());
        System.out.println(stackQueue.deQueue());
        stackQueue.enQueue(4);
        System.out.println(stackQueue.deQueue());
        System.out.println(stackQueue.deQueue());
    }
}
