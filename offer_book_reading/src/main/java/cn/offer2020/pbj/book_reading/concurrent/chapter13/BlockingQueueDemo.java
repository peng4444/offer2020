package cn.offer2020.pbj.book_reading.concurrent.chapter13;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: BlockingQueueDemo
 * @Author: pbj
 * @Date: 2020/5/22 15:44
 * @Description: TODO BlockingQueue阻塞队列使用
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        System.out.println("===============抛出异常==================");
        System.out.println(blockingQueue.add("a"));// add() 阻塞队列添加元素
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("d")); //会出现异常

        System.out.println(blockingQueue.element());// element() 阻塞队列检查 获取队列顶端元素

        System.out.println(blockingQueue.remove("a"));// remove() 阻塞队列移除元素
        System.out.println(blockingQueue.remove("A"));// false
        System.out.println("===============特殊值==================");
        System.out.println(blockingQueue.offer("x"));//添加元素 返回true
        System.out.println(blockingQueue.offer("x"));//不会异常 false

        System.out.println(blockingQueue.peek());//检查 返回 b

        System.out.println(blockingQueue.poll());//移除 返回 b

        System.out.println("=================一直阻塞=================");
        try {
            blockingQueue.put("z");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=================超时退出================");
        blockingQueue.offer("v", 2L, TimeUnit.SECONDS);//设置时延操作
        blockingQueue.offer("v", 2L, TimeUnit.SECONDS);
        blockingQueue.poll(2L, TimeUnit.SECONDS);
    }
}
