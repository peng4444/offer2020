package cn.offer2020.pbj.book_reading.concurrent.chapter4;

/**
 * @ClassName: JoinMethodTest
 * @Author: pbj
 * @Date: 2020/5/20 09:41
 * @Description: TODO join()方法示例
 */
public class JoinMethodTest {
    //未使用join()方法
    public static void UnUseJoin() {
        System.out.println("main thread start");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child thread start");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("child thread finshed");
            }
        });
        thread.start();
        System.out.println("main thread finshed");
    }
    //使用join()方法
    public static void userJoin() {
        System.out.println("main thread start");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child thread start");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("child thread finshed");
            }
        });
        thread.start();
        //加入join()方法等待子线程执行完毕，才执行主线程。
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread finshed");
    }
}
