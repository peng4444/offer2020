package cn.offer2020.pbj.book_reading.concurrent.chapter13;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SynchronizesQueueDemo
 * @Author: pbj
 * @Date: 2020/5/22 16:15
 * @Description: TODO SynchronizesQueue代码 加入一个，获取一个
 * AAA线程 put 1
 * BBB线程 -- 1
 * AAA线程 put 2
 * BBB线程 -- 2
 * AAA线程 put 3
 * BBB线程 -- 3
 */
public class SynchronizesQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> blockQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+" put 1");
                blockQueue.put("1");

                System.out.println(Thread.currentThread().getName()+" put 2");
                blockQueue.put("2");

                System.out.println(Thread.currentThread().getName()+" put 3");
                blockQueue.put("3");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA线程").start();

        new Thread(()->{
            try {
                //暂停一会线程
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" -- "+blockQueue.take());
                //暂停一会线程
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" -- "+blockQueue.take());
                //暂停一会线程
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" -- "+blockQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"BBB线程").start();
    }
}
