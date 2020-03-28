package cn.offer2020.pbj.javabasis.java.basis2.thread_1_2_3;

/**
 * 多线程的常用操作方法
 * 构造方法： public Thread(Runnable target,String name);
 * 设置名字： public final void setName(String name);
 * 取得名字： public final String getName();
 * 取得当前线程对象： public static Tread currentThread();
 * 线程休眠：public static void sleep(long millis) throws InterruptedException
 * 设置优先级：public final void setPriority(int newPriority);
 * 取得优先级: public final int getPriority();
 * 最高优先级： public static final int MAX_PRIORITY,10;
 * 中等优先级： public static final int NORM_PRIORITY,5;
 * 最低优先级： public static final int MIN_RIORITY,1;
 */
class MyThread3 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

class MyThread4 implements Runnable {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (int x = 0; x < 200; x++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ",x= " + x);

        }
    }

}

public class TestDemo {

    public static void main(String[] args) {
        MyThread3 mt = new MyThread3();
        new Thread(mt).start();
        new Thread(mt).start();
        new Thread(mt).start();
        new Thread(mt, "自己的线程对象A").start();
        mt.run();//?????
        MyThread4 mt1 = new MyThread4();
        new Thread(mt1, "自己的线程对象B").start();
        Thread t4 = new Thread(mt1, "自己的线程对象C");
        Thread t5 = new Thread(mt1, "自己的线程对象D");
        Thread t6 = new Thread(mt1, "自己的线程对象E");
        t4.setPriority(Thread.MAX_PRIORITY);
        t4.start();
        t5.start();
        t6.start();
        System.out.println(Thread.currentThread().getPriority());

    }

}
