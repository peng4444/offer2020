package cn.offer2020.pbj.book_reading.concurrent.chapter5;

/**
 * @ClassName: NoneClock
 * @Author: pbj
 * @Date: 2020/4/12 16:04
 * @Description: TODO 锁与同步 线程AB任意输出，每次不同
 */
public class NoneClock {

    static class ThreadA implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("线程TheadA:"+i);
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("线程TheadB:" + i);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
    }
}
