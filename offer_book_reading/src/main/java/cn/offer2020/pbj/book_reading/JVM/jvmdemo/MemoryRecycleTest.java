package cn.offer2020.pbj.book_reading.JVM.jvmdemo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MemoryRecycleTest
 * @Author: pbj
 * @Date: 2020/6/6 20:03
 * @Description: TODO [【JVM系列】一步步解析java执行内幕](https://www.cnblogs.com/wangjiming/p/10455993.html)
 * 通过一个小demo给大家演示一下CMS和G1两种垃圾回收器对物理内存归还的区别。
 * 首先使用CMS垃圾回收器：将jvm运行参数设置为：-Xms128M -Xmx2048M -XX:+UseConcMarkSweepGC
 * 使用G1垃圾回收器：-Xms128M -Xmx2048M -XX:+UseG1GC
 */
public class MemoryRecycleTest {
    @Test
    public void testMemoryRecycle() throws InterruptedException {

        List list = new ArrayList();

        //指定要生产的对象大小为512m
        int count = 512;

        //新建一条线程，负责生产对象
        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println(String.format("第%s次生产%s大小的对象", i, count));
                    addObject(list, count);
                    //休眠40秒
                    Thread.sleep(i * 10000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //新建一条线程，负责清理list，回收jvm内存
        new Thread(() -> {
            for (;;) {
                //当list内存到达512m，就通知gc回收堆
                if (list.size() >= count) {
                    System.out.println("清理list.... 回收jvm内存....");
                    list.clear();
                    //通知gc回收
                    System.gc();
                    //打印堆内存信息
                    printJvmMemoryInfo();
                }
            }
        }).start();

        //阻止程序退出
        Thread.currentThread().join();
    }

    public void addObject(List list, int count) {
        for (int i = 0; i < count; i++) {
            OOMobject ooMobject = new OOMobject();
            //向list添加一个1m的对象
            list.add(ooMobject);
            try {
                //休眠100毫秒
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class OOMobject{
        //生成1m的对象
        private byte[] bytes=new byte[1024*1024];
    }

    public static void printJvmMemoryInfo() {
        // 虚拟机级内存情况查询
        long vmFree = 0;
        long vmUse = 0;
        long vmTotal = 0;
        long vmMax = 0;
        int byteToMb = 1024 * 1024;
        Runtime rt = Runtime.getRuntime();
        vmTotal = rt.totalMemory() / byteToMb;
        vmFree = rt.freeMemory() / byteToMb;
        vmMax = rt.maxMemory() / byteToMb;
        vmUse = vmTotal - vmFree;
        System.out.println("");
        System.out.println("JVM内存已用的空间为：" + vmUse + " MB");
        System.out.println("JVM内存的空闲空间为：" + vmFree + " MB");
        System.out.println("JVM总内存空间为：" + vmTotal + " MB");
        System.out.println("JVM总内存最大堆空间为：" + vmMax + " MB");
        System.out.println("");
    }
}
