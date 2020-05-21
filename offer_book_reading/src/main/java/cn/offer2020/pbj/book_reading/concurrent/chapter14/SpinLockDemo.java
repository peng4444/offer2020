package cn.offer2020.pbj.book_reading.concurrent.chapter14;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName: SpinLockDemo
 * @Author: pbj
 * @Date: 2020/5/21 20:23
 * @Description: TODO 自旋锁代码
 * AA线程come in 0(n_n)o
 * AA线程come out 0(n_n)o
 * BB线程come in 0(n_n)o
 * BB线程come out 0(n_n)o
 */
public class SpinLockDemo {
    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"come in 0(n_n)o");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName()+"come out 0(n_n)o");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{
            spinLockDemo.myLock();
            //暂停一会线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"AA线程").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(()->{
            spinLockDemo.myLock();
            //暂停一会线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"BB线程").start();
    }
}
