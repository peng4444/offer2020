package cn.offer2020.pbj.book_reading.concurrent.chapter5;

import lombok.SneakyThrows;

/**
 * @ClassName: Recipient
 * @Author: pbj
 * @Date: 2020/5/9 09:18
 * @Description: TODO 收件人
 */
public class Recipient extends Thread {


    @SneakyThrows
    @Override
    public void run() {

        while (true) {
            synchronized (CourierCabinet.CABINET) {

                if (CourierCabinet.CABINET.size() == 0) {
                    // 快递员还未将包裹入柜 没法领取等一等
                    CourierCabinet.CABINET.wait();
                }

                CourierCabinet.CABINET.remove("包裹");
                System.out.println("哈哈 领到了我的快递...");
                CourierCabinet.CABINET.notify();
                Thread.sleep(100);
            }
        }
    }
}
