package cn.offer2020.pbj.book_reading.concurrent.chapter5;

/**
 * @ClassName: ObjectClock
 * @Author: pbj
 * @Date: 2020/4/12 16:10
 * @Description: TODO 加锁 线程A输出再线程B输出
 */
public class ObjectClock {
    private static Object lock = new Object();
    static class ThreadA implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("线程TheadA:"+i);
                }
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("线程TheadB:" + i);
                }
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
