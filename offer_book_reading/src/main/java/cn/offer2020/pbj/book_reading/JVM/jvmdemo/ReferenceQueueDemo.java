package cn.offer2020.pbj.book_reading.JVM.jvmdemo;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @ClassName: ReferenceQueueDemo
 * @Author: pbj
 * @Date: 2020/5/24 20:02
 * @Description: TODO 引用队列
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1, referenceQueue);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println(phantomReference);

        System.out.println("================================");
        o1 = null;
        System.gc();
        Thread.sleep(200);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println(phantomReference);
    }
}
