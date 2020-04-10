package cn.offer2020.pbj.book_reading.concurrent.chapter2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: CreateThread
 * @Author: pbj
 * @Date: 2020/4/9 21:12
 * @Description: TODO 多线程创建
 * [参考资料：Java多线程:进程和线程的概念和创建线程的三种方式](https://www.cnblogs.com/wmqBlog/p/9956235.html)
 * [参考资料：java中Future与FutureTask使用与分析](https://www.cnblogs.com/dafanjoy/p/9778271.html)
 */
public class CreateThread {

    //1.继承Thread
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("继承Thread类，创建线程");
        }
    }

    //2.实现Runnable接口
    public static class MyThread1 implements Runnable {

        @Override
        public void run() {
            System.out.println("实现Runnable接口，创建线程");
        }
    }
    //3.实现Callable接口
    public static class MyThread2 implements Callable {

        @Override
        public Object call() throws Exception {
            return "实现Runnable接口，创建线程";
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1.Thread类线程启动
        Thread thread1 = new MyThread();
        thread1.start();
        //2.Runnable接口线程启动
        MyThread1 thread2 = new MyThread1();
        new Thread(thread2).start();
        //3.Callable接口线程启动 FutureTask获取返回值
        MyThread2 thread3 = new MyThread2();
        FutureTask<Integer> futureTask = new FutureTask<>(thread3);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());

        //future类

    }
}
