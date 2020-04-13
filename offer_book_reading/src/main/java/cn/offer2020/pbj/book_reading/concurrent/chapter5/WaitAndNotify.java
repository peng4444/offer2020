package cn.offer2020.pbj.book_reading.concurrent.chapter5;

/**
 * @ClassName: WaitAndNotify
 * @Author: pbj
 * @Date: 2020/4/12 16:15
 * @Description: TODO 线程等待唤醒  线程AB接替打印
 */
public class WaitAndNotify {
    private static Object lock = new Object();
    static class ThreadA implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    try {
                        System.out.println("线程TheadA:" + i);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    try {
                        System.out.println("线程TheadB:" + i);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ThreadA()).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new ThreadB()).start();
    }
}
