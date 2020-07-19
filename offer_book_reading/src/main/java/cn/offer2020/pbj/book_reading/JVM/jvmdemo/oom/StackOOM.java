package cn.offer2020.pbj.book_reading.JVM.jvmdemo.oom;

import java.util.concurrent.TimeUnit;

/**
 * @pClassName: StackOOM
 * @author: pengbingjiang
 * @create: 2020/7/19 09:16
 * @description: TODO 栈内存溢出演示unable to create new native thread
 * VM Options:Xss512k 默认帧栈大小为1M，如果设置小了，可以创建更多线程。
 */
public class StackOOM {
    public static void infiniteRun() {
        while(true) {
            Thread thread = new Thread(() -> {

                while (true) {
                    try {
                        TimeUnit.HOURS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        infiniteRun();
    }
}
