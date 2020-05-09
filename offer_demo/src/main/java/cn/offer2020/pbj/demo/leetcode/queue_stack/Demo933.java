package cn.offer2020.pbj.demo.leetcode.queue_stack;

import java.util.ArrayDeque;

/**
 * @ClassName: Demo933
 * @Author: pbj
 * @Date: 2020/5/8 16:12
 * @Description: TODO 933. 最近的请求次数
 * JAVA实现：利用队列(FIFO, 先进先出) 进行实现。题目说的是，在时间点t进行一次ping操作，加上之前在 [t-3000, t] 范围内的ping操作的次数，并将次数返回。
 * 例如，例子中第一次ping的t=1， 返回1；第二次ping的t=100, 第一次ping的时间点1在本次允许范围[100-3000, 100]之内，所以返回2；
 * 第三次ping时，前两次的ping都在允许范围[3000-3000, 3000]之内，所以返回3；第四次ping时，第一次ping的t=1不在允许范围[3002-3000, 3000] 之内，
 * 所以返回3。利用队列先进先出的特点，移除当次ping操作不在允许范围内的时间点，剩下的队列内保存的都是允许范围内的时间点，最后返回队列的长度，即为当前时间点t所有允许范围内的ping操作次数。
 */
public class Demo933 {
    ArrayDeque<Integer> queue = new ArrayDeque<>();
    public Demo933() {

    }

    public int ping(int t) {
        while(!queue.isEmpty()){
            int val = queue.peek();
            if(val<t-3000){
                queue.pop();
            }else{
                break;
            }
        }
        queue.add(t);
        return queue.size();
    }
}
