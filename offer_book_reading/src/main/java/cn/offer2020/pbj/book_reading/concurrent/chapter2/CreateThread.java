package cn.offer2020.pbj.book_reading.concurrent.chapter2;

import java.util.concurrent.*;

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
        /* *
         * 功能描述: 1. 创建一个继承于Thread类的子类
                    2. 重写Thread类的run() --> 将此线程执行的操作声明在run()中
                    3. 创建Thread类的子类的对象
                    4. 通过此对象调用start()
         * @param: [args]
         * @return: void
         * @auther: pbj
         * @date: 2020/5/3 9:16
         */
        Thread thread1 = new MyThread();
        thread1.start();


        //2.Runnable接口线程 调用Thread启动
        /* *
         * 功能描述:
         * 1. 创建一个实现Runnable接口的类
             2. 实现Runnable中的run()方法
            3. 创建实现类的对象
            4. 将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
            5. 通过Thread类的对象调用start(
         * @param: [args]
         * @return: void
         * @auther: pbj
         * @date: 2020/5/3 9:16
         */
        MyThread1 thread2 = new MyThread1();
        new Thread(thread2).start();


        /* *
         * 功能描述:  1.创建Callable的实现类

        2.实现call方法，将此线程需要执行的操作声明在call()中

        3.创建Callable接口实现类的对象

        4.将此Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象

        5.将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()

        6.获取Callable中call方法的返回值
         * @param: [args]
         * @return: void
         * @auther: pbj
         * @date: 2020/5/3 9:15
         */
        //3.Callable接口线程 调用Thread启动 FutureTask获取返回值
        MyThread2 thread3 = new MyThread2();
        FutureTask<Integer> futureTask = new FutureTask<>(thread3);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
        //future类

        //1.提供指定线程数量的线程池
        //2.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        // 3.关闭连接池
        // 1. 提供指定线程数量的线程池  这里设置为10
        ExecutorService service = Executors.newFixedThreadPool(10);

        // 2.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        service.execute(new MyThread1());// 适合适用于Runnable
        // service.submit(Callable callable);//适合使用于Callable

        // 3.关闭连接池
        service.shutdown();

    }
}
