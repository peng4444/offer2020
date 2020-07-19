package cn.offer2020.pbj.book_reading.JVM.jvmdemo.oom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @pClassName: GCOverheadOOM
 * @author: pengbingjiang
 * @create: 2020/7/19 09:35
 * @description: TODO GC overhead limit exceeded GC -Xmx10m -Xms10m
 * JDK1.6之后新增了一个错误类型，如果堆内存太小的时候会报这个错误。
 * 如果98%的GC的时候回收不到2%的时候会报这个错误，也就是最小最大内存出现了问题的时候会报这个错误。
 */
public class GCOverheadOOM {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    //do nothing
                }
            });
        }
    }
}
