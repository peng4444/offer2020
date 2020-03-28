package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter5;

import java.util.Stack;

/**
 * @ClassName: Test2
 * @Author: pbj
 * @Date: 2019/10/17 10:05
 * @Description: TODO 最小栈的实现
 * 实现一个栈，该栈带有出栈（pop）、入栈（push）、取最小元素（getMin）3个方法。要保证这3个方法的时间复杂度都是O(1)。
 */
public class MinStack2 {
    /* *
     * 功能描述: 详细解法：
     *      1. 设原有的栈叫作栈A，此时创建一个额外的“备胎”栈B，用于辅助栈A。
     *      2. 当第1个元素进入栈A时，让新元素也进入栈B。这个唯一的元素是栈A的当前最小值。
     *      3. 之后，每当新元素进入栈A时，比较新元素和栈A当前最小值的大小，如果小于栈A当前最小值，则让新元素进入栈B，此时栈B的栈顶元素就是栈A当前最小值。
     *      4. 每当栈A有元素出栈时，如果出栈元素是栈A当前最小值，则让栈B的栈顶元素也出栈。此时栈B余下的栈顶元素所指向的，是栈A当中原本第2小的元素，代替刚才的出栈元素成为栈A的当前最小值。（备胎转正。）
     *      5. 当调用getMin方法时，返回栈B的栈顶所存储的值，这也是栈A的最小值。
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2019/10/17 10:07
     */
    private Stack<Integer> mainStack = new Stack<Integer>();
    private Stack<Integer> minStack = new Stack<Integer>();

    /*** 入栈操作* @param element 入栈的元*/
    public void push(int element) {
        mainStack.push(element);
        //如果辅助栈为空，或者新元素小于或等于辅助栈栈顶，则将新元素压入辅助栈
        if (minStack.empty() || element <= minStack.peek()) {
            minStack.push(element);
        }
    }

    /*** 出栈操作*/
    public Integer pop() {
        //如果出栈元素和辅助栈栈顶元素值相等，辅助栈出栈
        if (mainStack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        return mainStack.pop();
    }

    /*** 获取栈的最小元素*/
    public int getMin() throws Exception {
        if (mainStack.empty()) {
            throw new Exception("stack is empty");
        }
        return minStack.peek();
    }

    public static void main(String[] args) throws Exception {
        MinStack2 stack = new MinStack2();
        stack.push(4);
        stack.push(9);
        stack.push(7);
        stack.push(3);
        stack.push(8);
        stack.push(5);
        System.out.println(stack.getMin());
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.getMin());
    }
}
