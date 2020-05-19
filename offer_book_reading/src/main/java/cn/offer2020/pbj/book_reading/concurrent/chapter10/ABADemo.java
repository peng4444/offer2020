package cn.offer2020.pbj.book_reading.concurrent.chapter10;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName: ABADemo
 * @Author: pbj
 * @Date: 2020/5/19 18:01
 * @Description: TODO 模拟ABA问题
 * [【漫画】CAS原理分析！无锁原子类也能解决并发问题！](https://www.cnblogs.com/liuyanling/p/12913356.html)
 */
public class ABADemo {
    private static AtomicStampedReference<Integer> count = new AtomicStampedReference<>(10, 0);
    public static void main(String[] args) {
        Thread main = new Thread(() -> {
            int stamp = count.getStamp(); //获取当前版本

//            log.info("线程{} 当前版本{}",Thread.currentThread(),stamp);
            try {
                Thread.sleep(1000); //等待1秒 ，以便让干扰线程执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isCASSuccess = count.compareAndSet(10, 12, stamp, stamp + 1);  //此时expectedReference未发生改变，但是stamp已经被修改了,所以CAS失败
//            log.info("CAS是否成功={}",isCASSuccess);
        }, "主操作线程");

        Thread other = new Thread(() -> {
            int stamp = count.getStamp(); //获取当前版本
//            log.info("线程{} 当前版本{}",Thread.currentThread(),stamp);
            count.compareAndSet(10, 12, stamp, stamp + 1);
//            log.info("线程{} 增加后版本{}",Thread.currentThread(),count.getStamp());

            // 模拟ABA问题 先更新成12 又更新回10
            int stamp1 = count.getStamp(); //获取当前版本
            count.compareAndSet(12, 10, stamp1, stamp1 + 1);
//            log.info("线程{} 减少后版本{}",Thread.currentThread(),count.getStamp());
        }, "干扰线程");

        main.start();
        other.start();
    }
}
