package cn.offer2020.pbj.book_reading.concurrent.chapter3;

/**
 * @ClassName: ThreadGroup
 * @Author: pbj
 * @Date: 2020/4/10 16:01
 * @Description: TODO 线程组
 */
public class ThreadGroup {

    public static void main(String[] args) {
        Thread testThread = new Thread(()->{
            System.out.println("testThread当前线程组的名字："+Thread.currentThread().getThreadGroup().getName());
            System.out.println("testThread当前线程的名字："+Thread.currentThread().getName());
        });
        testThread.start();
        System.out.println("执行main方法线程名字："+Thread.currentThread().getName());
    }
    //输出
    //执行main方法线程名字：main
    //testThread当前线程组的名字：main
    //testThread当前线程的名字：Thread-0

}
