package cn.offer2020.pbj.book_reading.concurrent.chapter10;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @ClassName: AtomicLongAdderTest
 * @Author: pbj
 * @Date: 2020/5/15 20:07
 * @Description: TODO Atomic和LongAdder耗时测试
 * 随着并发的增加，AtomicLong性能是急剧下降的，耗时是LongAdder的数倍。
 */
public class AtomicLongAdderTest {
    public static void main(String[] args) throws Exception{
        testAtomicLongAdder(1, 10000000);
        testAtomicLongAdder(10, 10000000);
        testAtomicLongAdder(100, 10000000);
    }

    static void testAtomicLongAdder(int threadCount, int times) throws Exception{
        System.out.println("threadCount: " + threadCount + ", times: " + times);
        long start = System.currentTimeMillis();
        testLongAdder(threadCount, times);
        System.out.println("LongAdder 耗时：" + (System.currentTimeMillis() - start) + "ms");
        System.out.println("threadCount: " + threadCount + ", times: " + times);
        long atomicStart = System.currentTimeMillis();
        testAtomicLong(threadCount, times);
        System.out.println("AtomicLong 耗时：" + (System.currentTimeMillis() - atomicStart) + "ms");
        System.out.println("----------------------------------------");
    }

    static void testAtomicLong(int threadCount, int times) throws Exception{
        AtomicLong atomicLong = new AtomicLong();
        List<Thread> list = Lists.newArrayList();
        for (int i = 0; i < threadCount; i++) {
            list.add(new Thread(() -> {
                for (int j = 0; j < times; j++) {
                    atomicLong.incrementAndGet();
                }
            }));
        }

        for (Thread thread : list) {
            thread.start();
        }

        for (Thread thread : list) {
            thread.join();
        }

        System.out.println("AtomicLong value is : " + atomicLong.get());
    }

    static void testLongAdder(int threadCount, int times) throws Exception{
        LongAdder longAdder = new LongAdder();
        List<Thread> list = Lists.newArrayList();
        for (int i = 0; i < threadCount; i++) {
            list.add(new Thread(() -> {
                for (int j = 0; j < times; j++) {
                    longAdder.increment();
                }
            }));
        }

        for (Thread thread : list) {
            thread.start();
        }

        for (Thread thread : list) {
            thread.join();
        }

        System.out.println("LongAdder value is : " + longAdder.longValue());
    }
}
