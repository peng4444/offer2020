package cn.offer2020.pbj.book_reading.concurrent.chapter5;

import lombok.SneakyThrows;

/**
 * @ClassName: Courier
 * @Author: pbj
 * @Date: 2020/5/9 09:16
 * @Description: TODO 快递
 */
public class Courier extends Thread {
    @SneakyThrows
    @Override
    public void run() {

        synchronized (CourierCabinet.CABINET) {
            while (true) {
                if (CourierCabinet.CABINET.size() == 10) {
                    //歇一歇吧 快递柜没地方了
                    CourierCabinet.CABINET.wait();
                }
                CourierCabinet.CABINET.add("包裹");
                System.out.println("亲爱的顾客您的快递已入柜，请及时来领取");
                CourierCabinet.CABINET.notify();
                Thread.sleep(100);

            }
        }
    }
}
