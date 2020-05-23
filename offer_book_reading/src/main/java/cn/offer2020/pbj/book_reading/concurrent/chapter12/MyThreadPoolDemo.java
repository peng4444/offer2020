package cn.offer2020.pbj.book_reading.concurrent.chapter12;

import java.util.concurrent.*;

/**
 * @ClassName: MyThreadPoolDemo
 * @Author: pbj
 * @Date: 2020/5/23 19:35
 * @Description: TODO 自定义线程池（生产使用）ThreadPoolExecutor
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //ThreadPoolExecutor自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());//超过最大线程池size+阻塞队列容量会报异常
//                new ThreadPoolExecutor.CallerRunsPolicy());//超过最大线程池size+阻塞队列容量会返回(回退)调用者
//                new ThreadPoolExecutor.DiscardOldestPolicy());//超过最大线程池size+阻塞队列容量会丢弃一个最老的任务。
                new ThreadPoolExecutor.DiscardPolicy());//超过最大线程池size+阻塞队列容量直接丢弃多余的任务
        try {
            //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 1; i <= 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理业务。");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

}
