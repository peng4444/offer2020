package cn.offer2020.pbj.book_reading.concurrent.chapter10;

import sun.misc.Unsafe;

import static sun.net.InetAddressCachePolicy.get;

/**
 * @ClassName: AtomicIntegerDemo
 * @Author: pbj
 * @Date: 2020/4/13 13:39
 * @Description: TODO
 */
public abstract class AtomicIntegerDemo extends Number implements java.io.Serializable {
    //在没有锁的机制下,字段value要借助volatile原语，保证线程间的数据是可见性。
    private volatile int value;
    //Unsafe用于实现对底层资源的访问
    private static final Unsafe unsafe = Unsafe.getUnsafe();

    //valueOffset是value在内存中的偏移量
    private static final long valueOffset;
    //通过Unsafe获得valueOffset
    static {
        try {
            valueOffset = unsafe.objectFieldOffset(AtomicIntegerDemo.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    public final boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }

    public final int getAndIncrement() {//相当于++i操作
        for (;;) {
            int current = get();//获取值
            int next = current + 1;//+1操作
            if (compareAndSet(current, next))//current是预期值，即从主存中取来还未操作过的值，next更新后的值
                return current;
        }
    }
}
