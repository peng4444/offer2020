package cn.offer2020.pbj.book_reading.concurrent.chapter17;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: CountDownLatchDemo
 * @Author: pbj
 * @Date: 2020/4/18 17:01
 * @Description: TODO
 * 我们知道，玩游戏的时候，在游戏真正开始之前，一般会等待一些前置任务完成，比如“加载地图数据”，“加载人物模型”，
 * “加载背景音乐”等等。只有当所有的东西都加载完成后，玩家才能真正进入游戏。下面我们就来模拟一下这个demo。
 */
public class CountDownLatchDemo {
    // 定义前置任务线程
    static class PreTaskThread implements Runnable {
        private String task;
        private CountDownLatch countDownLatch;
        public PreTaskThread(String task, CountDownLatch countDownLatch) {
            this.task = task;
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            try {
                Random random = new Random();
                Thread.sleep(random.nextInt(1000));
                System.out.println(task + " - 任务完成");
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        // 假设有三个模块需要加载
        CountDownLatch countDownLatch = new CountDownLatch(3);
        // 主任务
        new Thread(() -> {
            try {
                System.out.println("等待数据加载...");
                System.out.println(String.format("还有%d个前置任务", countDownLatch.getCount()));
                countDownLatch.await();
                System.out.println("数据加载完成，正式开始游戏！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        // 前置任务
        new Thread(new PreTaskThread("加载地图数据", countDownLatch)).start();
        new Thread(new PreTaskThread("加载人物模型", countDownLatch)).start();
        new Thread(new PreTaskThread("加载背景音乐", countDownLatch)).start();
    }

    /* *
     * 功能描述: 多线程模拟秦国灭亡六国，统一。
     * @param: []
     * @return: void
     * @auther: pbj
     * @date: 2020/5/22 14:42
     */
    @Test
    public void SixCOuntry() throws InterruptedException {
        //使用CountDownLatch 才能保证所有的线程走完成。
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"国，灭亡");
                countDownLatch.countDown();
            },CountryEnum.foreach_CountryEnum(i).getRetName()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"*****秦国统一");
    }


    /* *
     * 功能描述: 代码演示多线程输出和CountDownLatch控制线程
     * @param: []
     * @return: void
     * @auther: pbj
     * @date: 2020/5/22 14:22
     */
    @Test
    public void UnCountDownLatchDemo() throws InterruptedException {
        //使用CountDownLatch 才能保证所有的线程走完成。
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"上完自习，离开教室");
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"*****班长最后走人");
    }
}
