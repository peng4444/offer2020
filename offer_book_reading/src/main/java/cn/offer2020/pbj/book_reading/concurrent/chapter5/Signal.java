package cn.offer2020.pbj.book_reading.concurrent.chapter5;

/**
 * @ClassName: Signal
 * @Author: pbj
 * @Date: 2020/4/12 16:26
 * @Description: TODO  volatile 变量信号量 线程接替递增输出 A0 B1 A2 B3
 */
public class Signal {

    private static volatile int signal = 0;

    static class ThreadA implements Runnable {

        @Override
        public void run() {
            while (signal < 5) {
                if (signal % 2 == 0) {
                    System.out.println("线程ThreadA：" + signal);
                    synchronized (this) {
                        signal++;
                    }
                }
            }
        }
    }

    static class ThreadB implements Runnable {

        @Override
        public void run() {
            while (signal < 5) {
                if (signal % 2 == 1) {
                    System.out.println("线程ThreadB：" + signal);
                    synchronized (this) {
                        signal=signal+1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(10);
        new Thread(new ThreadB()).start();
    }
}
