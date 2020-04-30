package cn.offer2020.pbj.book_reading.concurrent.chapter17;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CountDownLatchDemo1
 * @Author: pbj
 * @Date: 2020/4/30 16:53
 * @Description: TODO //场景：主人（主线程）请客人（子线程）吃晚饭，需要等待所有客人都到了之后才开饭。
 */
public class CountDownLatchDemo1 {
    private static final int PERSON_COUNT = 5;
    private static final CountDownLatch c = new CountDownLatch(PERSON_COUNT);
    public static void main(String[] args) throws InterruptedException {
        System.out.println("l am master, waiting guests...");
        for (int i = 0; i < PERSON_COUNT; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" l am person["+ finalI +"]");
                    TimeUnit.MILLISECONDS.sleep(500);
                    //System.out.println(Thread.currentThread().getName()+" count:"+c.getCount());
                    c.countDown();
                }
            }).start();
        }
        c.await();
        System.out.println("all guests get, begin dinner...");
    }
}
//上面的列子中，主人（master线程）请了5个客人吃饭，每个客人到了之后会将CountDownLatch的值减一，主人（master）会一直等待所有客人到来，最后输出”开饭“。
