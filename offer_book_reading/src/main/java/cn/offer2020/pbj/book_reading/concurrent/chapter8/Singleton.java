package cn.offer2020.pbj.book_reading.concurrent.chapter8;

/**
 * @ClassName: Singleton
 * @Author: pbj
 * @Date: 2020/4/13 10:42
 * @Description: TODO 双重锁检查实现单例模式
 */
public class Singleton {
    private static volatile Singleton instance = null; // volatile可以保证可见性和有序性防止指令重排

    private Singleton() {
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法Singleton()");
    }
    // 双重锁检验
    public static Singleton getInstance() {
        if (instance == null) { // 第7行
            synchronized (Singleton.class) { // sychronized保证操作的原子性
                if (instance == null) {
                    instance = new Singleton(); // 第10行
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(Singleton.getInstance() == Singleton.getInstance());

        //并发多线程环境，
        for (int i = 0; i <= 10; i++) {
            new Thread(() ->{
                Singleton.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
