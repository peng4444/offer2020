package cn.offer2020.pbj.demo.leetcode.a_maths;

import java.util.PriorityQueue;

/**
 * @ClassName: Demo703
 * @Author: pbj
 * @Date: 2019/12/12 19:58
 * @Description: TODO 数据流中的第K大元素
 */
public class Demo703 {
    private PriorityQueue<Integer> queue;
    private int limit;
    /* *
     * 功能描述: Java使用最小堆来实现（使用内置优先级队列 PriorityQueue）
     * 2.普通方法，先将数组排序，再取第K大数
     * @param: [k, nums]
     * @return:
     * @auther: pbj
     * @date: 2019/12/12 20:06
     */
    public Demo703(int k, int[] nums) {
        limit = k;
        queue = new PriorityQueue<>(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (queue.size() < limit) {
            queue.add(val);
        } else if (val > queue.peek()) {
            queue.poll();
            queue.add(val);
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arr = new int[]{4, 8, 3, 2};
        Demo703 demo703 = new Demo703(k, arr);
        demo703.add(3);
        demo703.add(5);
        demo703.add(10);
        demo703.add(9);
        demo703.add(4);
    }
}
