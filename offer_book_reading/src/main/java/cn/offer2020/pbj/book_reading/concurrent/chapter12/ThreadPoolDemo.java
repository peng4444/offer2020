package cn.offer2020.pbj.book_reading.concurrent.chapter12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadPoolDemo
 * @Author: pbj
 * @Date: 2020/5/23 16:47
 * @Description: TODO 4种获得/使用java多线程的方式，线程池
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//一个线程池5个处理线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//一个线程池1个处理线程
        ExecutorService threadPool = Executors.newCachedThreadPool();//线程池N个处理线程
        try {
            //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理业务。");
                });
                //暂停一会线程
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }
}
