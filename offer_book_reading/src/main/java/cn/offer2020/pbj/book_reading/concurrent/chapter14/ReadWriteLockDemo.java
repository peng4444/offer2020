package cn.offer2020.pbj.book_reading.concurrent.chapter14;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName: ReadWriteLockDemo
 * @Author: pbj
 * @Date: 2020/5/21 20:43
 * @Description: TODO 读写锁/独占锁共享锁
 * 多个线程同时读一个资源没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是有线程想去写共享线程，就不能有其他线程对资源进行读或者写。
 * 读-读能共存
 * 读-写不能共存
 * 写-写不能共存
 * 写操作：原子+独占，整个过程必须是一个完整的统一体，不能被分割和打断。
 *2正在写入:2
 * 2写入完成:
 * 1正在写入:1
 * 1写入完成:
 * 3正在写入:3
 * 3写入完成:
 * 4正在写入:4
 * 4写入完成:
 * 5正在写入:5
 * 5写入完成:
 * 2正在读取:
 * 3正在读取:
 * 1正在读取:
 * 4正在读取:
 * 5正在读取:
 * 3读取完成:3
 * 2读取完成:2
 * 1读取完成:1
 * 4读取完成:4
 * 5读取完成:5
 * 3读取完成:null
 */
//我的资源类
class MyCache{
    private volatile Map<String, Object> map = new HashMap<>();
    //    private Lock lock = new ReentrantLock();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在写入:" + key);
            //暂停一会线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完成:");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在读取:");
            //暂停一会线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object res = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取完成:" + res);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }

    }

}
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            final  int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt+"",tempInt);
            },String.valueOf(i).toString()).start();
        }

        for (int i = 1; i <= 5; i++) {
            final  int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt+"");
            },String.valueOf(i).toString()).start();
        }
    }
}
