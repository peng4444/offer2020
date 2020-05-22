package cn.offer2020.pbj.book_reading.concurrent.chapter17;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SemaphoreDemo
 * @Author: pbj
 * @Date: 2020/4/18 16:54
 * @Description: TODO Semaphore往往用于资源有限的场景中，去限制线程的数量。
 * 举个例子，我想限制同时只能有3个线程在工作.
 */
public class SemaphoreDemo {
    static class MyThread implements Runnable {
        private int value;
        private Semaphore semaphore;
        public MyThread(int value, Semaphore semaphore) {
            this.value = value;
            this.semaphore = semaphore;
        }
        @Override
        public void run() {
            try {
                semaphore.acquire(); // 获取permit
                System.out.println(String.format("当前线程是%d, 还剩%d个资源，还有%d个线程在等待",
                        value, semaphore.availablePermits(), semaphore.getQueueLength()));
                // 睡眠随机时间，打乱释放顺序
                Random random =new Random();
                Thread.sleep(random.nextInt(1000));
                semaphore.release(); // 释放permit
                System.out.println(String.format("线程%d释放了资源", value));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(new MyThread(i, semaphore)).start();
        }
    }

    /* *
     * 功能描述: Semaphore模拟抢车位
     * @param: []
     * @return: void
     * @auther: pbj
     * @date: 2020/5/22 15:04
     */
    @Test
    public void getCar(){
        Semaphore semaphore = new Semaphore(3);//模拟有三个车位
        for (int i = 1; i <= 6; i++) {//模拟有六部汽车
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"停车3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
