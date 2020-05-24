package cn.offer2020.pbj.book_reading.JVM.jvmdemo;

import java.lang.ref.WeakReference;

/**
 * @ClassName: WeakReferenceDemo
 * @Author: pbj
 * @Date: 2020/5/24 19:48
 * @Description: TODO 弱引用
 */
public class WeakReferenceDemo {
    public static void sofeRef_Memory_Enough() {
        Object o1 = new Object();
        WeakReference<Object> softReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();

        System.out.println("==========================");

        System.out.println(o1);
        System.out.println(softReference.get());
    }
}
