package cn.offer2020.pbj.book_reading.JVM.jvmdemo;

import java.lang.ref.SoftReference;

/**
 * @ClassName: SoftReferenceDemo
 * @Author: pbj
 * @Date: 2020/5/24 19:32
 * @Description: TODO 软引用
 */
public class SoftReferenceDemo {

    //内存够用的时候就保留，不够用就回收。
    public static void sofeRef_Memory_Enough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(softReference.get());
    }

    //JVM配置 故意产生大对象并配置小的内存，让他内存不够用导致OOM,看弱引用的回收情况
    //-Xms5m -Xmx5m -XX:+PrintGCDetails
    public static void softRef_Memory_NotEnough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Throwable e) {
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }




    }
}
