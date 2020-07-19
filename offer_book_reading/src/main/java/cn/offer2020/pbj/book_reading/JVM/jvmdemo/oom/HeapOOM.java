package cn.offer2020.pbj.book_reading.JVM.jvmdemo.oom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @pClassName: HeapOOM
 * @author: pengbingjiang
 * @create: 2020/7/19 09:06
 * @description: TODO 堆内存溢出演示 VM Options: -Xmx100m
 */
public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();

        while(true) {
            TimeUnit.MILLISECONDS.sleep(1);
            list.add(new OOMObject());
        }
    }
}
