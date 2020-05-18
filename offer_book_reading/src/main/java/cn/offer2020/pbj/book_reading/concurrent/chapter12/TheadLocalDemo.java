package cn.offer2020.pbj.book_reading.concurrent.chapter12;

/**
 * @ClassName: TheadLocalDemo
 * @Author: pbj
 * @Date: 2020/5/18 16:00
 * @Description: TODO TheadLocal代码例子
 * 线程1和线程2虽然使用的是同一个ThreadLocal变量存储值，但是输出结果中，两个线程的值却互不影响，线程1从1输出到10，而线程2从100输出到91。
 * 这就是ThreadLocal的功能，即让每一个线程拥有自己独立的变量，多个线程之间互不影响。
 */
public class TheadLocalDemo {
    // 定义一个线程共享的ThreadLocal变量
    static ThreadLocal<Integer> tl = new ThreadLocal<>();

    public static void main(String[] args) {

        // 创建第一个线程
        Thread t1 = new Thread(() -> {
            // 设置ThreadLocal变量的初始值，为1
            tl.set(1);
            // 循环打印ThreadLocal变量的值
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "----" + tl.get());
                // 每次打印完让值 + 1
                tl.set(tl.get() + 1);
            }
        }, "thread1");

        // 创建第二个线程
        Thread t2 = new Thread(() -> {
            // 设置ThreadLocal变量的初始值，为100，与上一个线程区别开
            tl.set(100);
            // 循环打印ThreadLocal变量的值
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "----" + tl.get());
                // 每次打印完让值 - 1
                tl.set(tl.get() - 1);
            }
        }, "thread2");
        // 开启两个线程
        t1.start();
        t2.start();

        tl.remove();
    }

}
