package cn.offer2020.pbj.book_reading.concurrent.chapter14;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ReenterLockDemo
 * @Author: pbj
 * @Date: 2020/5/21 19:41
 * @Description: TODO 可重入锁代码演示 和 可重入锁避免死锁。
 * 就是支持重新进入的锁，也就是说这个锁支持一个线程对资源重复加锁。线程可以进入任何一个它拥有的锁所同步着的代码块
 */
class Phone implements Runnable {
    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getName() + "invoked sendSMS().");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getName() + "#### invoked sendEmail().");
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "invoked get().");
            set();
        }finally {
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "### invoked set().");
        }finally {
            lock.unlock();
        }
    }
}
/* *
 * 功能描述:
 *  t1线程invoked sendSMS().
    t1线程#### invoked sendEmail().
    t2线程invoked sendSMS().
    t2线程#### invoked sendEmail().
 * @param:
 * @return:
 * @auther: pbj
 * @date: 2020/5/21 19:49
 */
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1线程").start();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2线程").start();


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(phone,"t3线程");
        Thread thread1 = new Thread(phone,"t4线程");
        thread.start();
        thread1.start();
    }
}
