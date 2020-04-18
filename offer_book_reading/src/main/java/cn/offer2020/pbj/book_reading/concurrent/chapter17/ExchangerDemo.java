package cn.offer2020.pbj.book_reading.concurrent.chapter17;

import java.util.concurrent.Exchanger;

/**
 * @ClassName: ExchangerDemo
 * @Author: pbj
 * @Date: 2020/4/18 16:59
 * @Description: TODO
 * Exchanger类用于两个线程交换数据。它支持泛型，也就是说你可以在两个线程之间传送任何数据。
 * 先来一个案例看看如何使用，比如两个线程之间想要传送字符串：
 */
public class ExchangerDemo {

    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(() -> {
            try {
                System.out.println("这是线程A，得到了另一个线程的数据："
                        + exchanger.exchange("这是来自线程A的数据"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("这个时候线程A是阻塞的，在等待线程B的数据");
        Thread.sleep(1000);
        new Thread(() -> {
            try {
                System.out.println("这是线程B，得到了另一个线程的数据："
                        + exchanger.exchange("这是来自线程B的数据"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
