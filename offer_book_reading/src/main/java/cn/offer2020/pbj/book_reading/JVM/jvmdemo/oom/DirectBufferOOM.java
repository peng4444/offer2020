package cn.offer2020.pbj.book_reading.JVM.jvmdemo.oom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @pClassName: DirectBufferOOM
 * @author: pengbingjiang
 * @create: 2020/7/19 09:34
 * @description: TODO 直接内存溢出 -Xmx128m -XX:MaxDirectMemorySize=100M
 * 设置堆最大内存为128m，直接内存为100m，然后我们每次分配1M放到list里边。　
 */
public class DirectBufferOOM {
    public static void main(String[] args) {
        final int _1M = 1024 * 1024 * 1;
        List<ByteBuffer> buffers = new ArrayList<>();
        int count = 1;
        while (true) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_1M);
            buffers.add(byteBuffer);
            System.out.println(count++);
        }
    }
}
