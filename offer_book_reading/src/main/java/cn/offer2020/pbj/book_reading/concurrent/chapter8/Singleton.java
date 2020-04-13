package cn.offer2020.pbj.book_reading.concurrent.chapter8;

/**
 * @ClassName: Singleton
 * @Author: pbj
 * @Date: 2020/4/13 10:42
 * @Description: TODO 双重锁检查实现单例模式
 */
public class Singleton {
    private static Singleton instance; // 不使用volatile关键字
    // 双重锁检验
    public static Singleton getInstance() {
        if (instance == null) { // 第7行
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton(); // 第10行
                }
            }
        }
        return instance;
    }
}
