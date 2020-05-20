package cn.offer2020.pbj.book_reading.concurrent.chapter8;

import org.junit.Test;

/**
 * @ClassName: VolatileExample
 * @Author: pbj
 * @Date: 2020/4/13 10:15
 * @Description: TODO volatile代码示例，变量解决可见性，但是不保证原子性
 */
public class VolatileExample {
    int a = 0;
    volatile  boolean flag = false;//Java内存可见性volatile

    public void write() {
        a = 1;//step1
        flag = true;//step2
    }

    public void reader() {
        if (flag) {//step3
            System.out.println(a);//step4
        }
    }

    //可见性问题出现  count极小概率会出现等于0的情况
    private static int count = 0;//volatile修饰变量解决Java内存可见性
    @Test
    public void testDemo() throws Exception {
        Thread th1 = new Thread(() -> {
            count = 10;
        });
        Thread th2 = new Thread(() -> {
            //极小概率会出现等于0的情况
            System.out.println("count=" + count);
        });
        th1.start();
        th2.start();
    }

    //volatile修饰变量解决Java内存可见性,但是不保证原子性 count1++不是原子性操作
    private volatile long count1 = 0;
    private void add10K() {  //使用synchronize(独占锁/排它锁) 同时只有一个线程调用count1++
        int idx = 0;
        while (idx++ < 10000) {
            count1++;
            // AtomicInteger atomicInteger = new AtomicInteger();
            // atomicInteger.getAndIncrement();//可以保证原子性
        }
    }

    @Test // 测试代码
    public void testDemo1() throws InterruptedException {
        VolatileExample test = new VolatileExample();
        // 创建两个线程，执行 add() 操作
        Thread th1 = new Thread(()->{
            test.add10K();
        });
        Thread th2 = new Thread(()->{
            test.add10K();
        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        // 介于1w-2w,即使加了volatile也达不到2w
        System.out.println(test.count1);
    }

    // volatile 可见性
    // 共享变量
    static int counter = 0;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            int temp = 0;
            while (true) {
                if (temp != counter) {
                    temp = counter;
                    // 打印counter的值，期望打印 12345
                    System.out.print(counter);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                counter++;
                // 等待1秒，给读线程足够的时间读取变量counter的最新值
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 退出程序
            System.exit(0);
        });

        thread1.start();
        thread2.start();
    }
}
