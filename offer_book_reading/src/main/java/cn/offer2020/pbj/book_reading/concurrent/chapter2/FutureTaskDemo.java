package cn.offer2020.pbj.book_reading.concurrent.chapter2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @pClassName: FutureTaskDemo
 * @author: pengbingjiang
 * @create: 2020/7/1 15:45
 * @description: TODO Future是如何使用的
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() + ":" + "开始烧开水...");
                // 模拟烧开水耗时
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + ":"  + "开水已经烧好了...");
                return "开水";
            }
        });

        Thread thread = new Thread(futureTask);
        thread.start();

        // do other thing
        System.out.println(Thread.currentThread().getName() + ":"  + " 此时开启了一个线程执行future的逻辑（烧开水），此时我们可以干点别的事情（比如准备火锅食材）...");
        // 模拟准备火锅食材耗时
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + ":"  + "火锅食材准备好了");
        String shicai = "火锅食材";

        // 开水已经稍好，我们取得烧好的开水
        String boilWater = futureTask.get();

        System.out.println(Thread.currentThread().getName() + ":"  + boilWater + "和" + shicai + "已经准备好，我们可以开始打火锅啦");
    }
}
