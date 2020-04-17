package cn.offer2020.pbj.book_reading.concurrent.chapter15;

import java.util.Vector;

/**
 * @ClassName: TestVector
 * @Author: pbj
 * @Date: 2020/4/17 23:00
 * @Description: TODO 测试Vector
 * 即使是Vector这样线程安全的类，在面对多线程下的复合操作的时候也是需要通过客户端加锁的方式保证原子性。
 * 如果方法一和方法二为一个组合的话。那么当方法一获取到了vector的size之后，方法二已经执行完毕，这样就导致程序的错误。
 * 如果方法三与方法四组合的话。通过锁机制保证了在vector上的操作的原子性。
 */
public class TestVector {
    private Vector<String> vector;

    //方法一
    public  Object getLast(Vector vector) {
        int lastIndex = vector.size() - 1;
        return vector.get(lastIndex);
    }

    //方法二
    public  void deleteLast(Vector vector) {
        int lastIndex = vector.size() - 1;
        vector.remove(lastIndex);
    }

    //方法三
    public  Object getLastSysnchronized(Vector vector) {
        synchronized(vector){
            int lastIndex = vector.size() - 1;
            return vector.get(lastIndex);
        }
    }

    //方法四
    public  void deleteLastSysnchronized(Vector vector) {
        synchronized (vector){
            int lastIndex = vector.size() - 1;
            vector.remove(lastIndex);
        }
    }

}
