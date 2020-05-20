package cn.offer2020.pbj.book_reading.concurrent.chapter10;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: CASDemo
 * @Author: pbj
 * @Date: 2020/5/19 17:29
 * @Description: TODO CAS （compareAndSwap），中文叫比较交换，一种无锁原子算法。
 * [【漫画】CAS原理分析！无锁原子类也能解决并发问题！](https://www.cnblogs.com/liuyanling/p/12913356.html)
 * 当且仅当V的值等于期待值A时,CAS通过原子方式用新值B来更新V的值，否则不会执行任何操作（比较和替换是一个原子操作）。
 */
public class CASDemo {
    // count必须用volatile修饰 保证不同线程之间的可见性
    private volatile static int count;
    private int expectCount = 1204;

    public void addOne() {
        int newValue;
        do {
            newValue = count++;
        } while (!compareAndSwapInt(expectCount, newValue)); //自旋 循环
    }

    public final boolean compareAndSwapInt(int expectCount, int newValue) {
        // 读目前 count 的值
        int curValue = count;
        // 比较目前 count 值是否 == 期望值
        if (curValue == expectCount) {
            // 如果是，则更新 count 的值
            count = newValue;
            return true;

        }
        //否则返回false 然后循环
        return false;
    }

    private static AtomicInteger count1 = new AtomicInteger(0);
    private static int count2 = 0;
    private static CountDownLatch countDownLatch = new CountDownLatch(0);
    //省略代码 同时启动10个线程 分别测试AtomicInteger和普通int的输出结果
    private static void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            //使用incrementAndGet实现i++功能
            count1.incrementAndGet();
        }
        countDownLatch.countDown();
    }
    private static void add10K1() {
        int idx = 0;
        while (idx++ < 10000) {
            count2++;
        }
        countDownLatch.countDown();
    }
}
