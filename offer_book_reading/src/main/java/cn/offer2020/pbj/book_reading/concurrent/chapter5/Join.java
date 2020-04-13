package cn.offer2020.pbj.book_reading.concurrent.chapter5;

/**
 * @ClassName: Join
 * @Author: pbj
 * @Date: 2020/4/12 16:47
 * @Description: TODO Join join()⽅法及其重载⽅法底层都是利⽤了wait(long)这个⽅法
 */
public class Join {

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("我是⼦线程，我先睡⼀秒");
                Thread.sleep(1000);
                System.out.println("我是⼦线程，我睡完了⼀秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadA());
        thread.start();
        thread.join();
        System.out.println("如果不加join⽅法，我会先被打出来，加了就不⼀样了");
    }
}
