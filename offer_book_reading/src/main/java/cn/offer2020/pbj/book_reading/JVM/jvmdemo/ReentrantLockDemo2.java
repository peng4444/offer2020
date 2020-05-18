package cn.offer2020.pbj.book_reading.JVM.jvmdemo;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ReentrantLockDemo2
 * @Author: pbj
 * @Date: 2020/5/13 18:32
 * @Description: TODO  ReentrantLock可指定是公平锁还是非公平锁，而synchronized只能是非公平锁。
 */
public class ReentrantLockDemo2 {
    // 分别测试为true 和 为false的输出。为true则输出顺序一定是A B C 但是为false的话有可能输出A C B
    private static final ReentrantLock reentrantLock = new ReentrantLock(true);
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo2 demo2 = new ReentrantLockDemo2();
        Thread a = new Thread(() -> { test(); }, "A");
        Thread b = new Thread(() -> { test(); }, "B");
        Thread c = new Thread(() -> { test(); }, "C");
        a.start();b.start();c.start();

    }
    @Test
    public static void test() {
        reentrantLock.lock();
        try {
            System.out.println("线程" + Thread.currentThread().getName());
        } finally {
            reentrantLock.unlock();//一定要释放锁
        }
    }
}
