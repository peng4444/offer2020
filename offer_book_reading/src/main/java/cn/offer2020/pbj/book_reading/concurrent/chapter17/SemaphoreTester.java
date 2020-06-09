package cn.offer2020.pbj.book_reading.concurrent.chapter17;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @ClassName: SemaphoreTester
 * @Author: pbj
 * @Date: 2020/6/9 09:31
 * @Description: TODO 可以看到，当已经有两个人在洗手的时候，其他人就被阻塞，直到有人洗手完毕才是开始洗手。
 */
public class SemaphoreTester {
    public static void main(String[] args) throws InterruptedException {
        //饭店里只用两个洗手池，所以初始化许可证的总数为2。
        Semaphore washbasin = new Semaphore(2);

        List<Thread> threads = new ArrayList<>(3);
        threads.add(new Thread(new Customer(washbasin, "张三")));
        threads.add(new Thread(new Customer(washbasin, "李四")));
        threads.add(new Thread(new Customer(washbasin, "王五")));
        threads.add(new Thread(new Customer(washbasin, "赵六")));
        for (Thread thread : threads) {
            thread.start();
            Thread.sleep(50);
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}
