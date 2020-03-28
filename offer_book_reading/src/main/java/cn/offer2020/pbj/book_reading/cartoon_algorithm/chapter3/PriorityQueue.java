package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter3;

import java.util.Arrays;

/**
 * @ClassName: PriorityQueue
 * @Author: pbj
 * @Date: 2019/9/16 15:32
 * @Description: TODO  优先队列 优先队列入队和出队的时间复杂度是O(logn)
 */
public class PriorityQueue {
    private int[] array;
    private int size;

    public PriorityQueue() {
        //队列初始长度为32
        array = new int[32];
    }

    //入队
    public void enQueue(int key) {
        //如果队列长度超出范围，扩容
        if (size >= array.length) {
            resize();
        }
        array[size++] = key;
        upAdjust();
    }

    //出队
    public int deQueue() throws Exception {
        if (size <= 0) {
            throw new Exception("the queue is empty!");
        }
        //获取堆顶元素
        int head = array[0];
        //让最后一个元素移动到堆顶
        array[0] = array[--size];
        downAdjust();
        return head;
    }

    //上浮
    private void upAdjust() {
        int childIndex = size -1;
        int parentIndex = (childIndex - 1) / 2;
        //temp 保存插入的叶子节点值，用于最后的赋值
        int temp = array[childIndex];
        while (childIndex > 0 && temp > array[parentIndex]) {
            //无须真正交换，返现赋值即可
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = parentIndex / 2;
        }
        array[childIndex] = temp;
    }

    //下沉
    public void downAdjust() {
        //temp 保存插入的叶子节点值，用于最后的赋值
        int parentIndex = 0;
        int temp = array[parentIndex];
        int childIndex = 0;
        while (childIndex < size) {
            //如果有右孩子，并且右孩子大于左边孩子的值，则定位到右孩子
            if (childIndex + 1 < size && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }
            //如果父节点大于任何一个孩子的值，则直接跳出
            if (temp >= array[childIndex]) {
                break;
            }
            //无须真正交换，返现赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2*childIndex+1;
        }
        array[parentIndex] = temp;
    }
    //扩容
    private void resize() {
        //队列扩容
        int newSize = this.size*2;
        this.array = Arrays.copyOf(this.array, newSize);
    }

    public static void main(String[] args) throws Exception {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enQueue(3);
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(10);
        priorityQueue.enQueue(2);
        priorityQueue.enQueue(7);
        System.out.println("出队元素："+priorityQueue.deQueue());
        System.out.println("出队元素"+priorityQueue.deQueue());
    }
}
