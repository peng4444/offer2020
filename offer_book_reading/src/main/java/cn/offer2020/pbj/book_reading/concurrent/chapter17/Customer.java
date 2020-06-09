package cn.offer2020.pbj.book_reading.concurrent.chapter17;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @ClassName: Customer
 * @Author: pbj
 * @Date: 2020/6/9 09:30
 * @Description: TODO 张三、李四和王五和赵六4个人一起去饭店吃饭，不过在特殊时期洗手很重要，饭前洗手也是必须的，
 *  可是饭店只有2个洗手池，洗手池就是不能被同时使用的公共资源，这种场景就可以用到Semaphore。
 */
public class Customer implements Runnable {
    private Semaphore washbasin;
    private String name;

    public Customer(Semaphore washbasin, String name) {
        this.washbasin = washbasin;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
            Random random = new Random();

            washbasin.acquire();
            System.out.println(
                    sdf.format(new Date()) + " " + name + " 开始洗手...");
            Thread.sleep((long) (random.nextDouble() * 5000) + 2000);
            System.out.println(
                    sdf.format(new Date()) + " " + name + " 洗手完毕!");
            washbasin.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
