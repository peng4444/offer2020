package cn.offer2020.pbj.javabasis.java.basis2.thread_1_2_3;

/**
 * @ClassName: ThreadStop
 * @Author: pbj
 * @Date: 2020/3/28 10:07
 * @Description: TODO 线程中止
 * [如何优雅地中止线程？](https://www.cnblogs.com/wupeixuan/p/12578851.html)
 * 那 stop() 和 interrupt() 方法的主要区别是什么呢？
 * stop() 方法会真的杀死线程，如果线程持有 ReentrantLock 锁，被 stop() 的线程并不会自动调用 ReentrantLock 的 unlock() 去释放锁，
 * 那其他线程就再也没机会获得 ReentrantLock 锁，这实在是太危险了。所以该方法就不建议使用了，类似的方法还有 suspend() 和 resume() 方法，
 * 这两个方法同样也都不建议使用。
 * 而 interrupt() 方法仅仅是通知线程，线程有机会执行一些后续操作，同时也可以无视这个通知。被 interrupt 的线程，
 * 是怎么收到通知的呢？一种是异常，另一种是主动检测。
 */
public class ThreadStop extends Thread {

    private int j = 0;
    private int i = 0;

    @Override
    public void run() {
        synchronized (this) {
            // 增加同步锁，确保线程安全    同步代码块
            ++i;
            try {
                //休眠10S,模拟好使操作
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ++j;
        }
    }

    //打印 i,j
    public void print() {
        System.out.println("i:"+i+"    "+"j:"+j);
    }

    //// stop 中止线程
    public static void main(String[] args) throws InterruptedException {
        ThreadStop threadStop = new ThreadStop();
        threadStop.start();
        // 休眠1S,确保 i 变量自增成功
        Thread.sleep(1000);
        //中止线程
        //threadStop.stop();//不推荐
        threadStop.interrupt();//interrupt 不会强制中止，将线程直接中断，而是抛出异常通知我们，开发者就可以控制收到
        // 异常后的执行逻辑，让整个程序处于线程安全的状态，这是目前 JDK 版本中推荐的 interrupt 方法。
        while (threadStop.isAlive()) {
            //确保线程中止
        }
        //输出结果
        threadStop.print();
    }
        //正确的线程中止 - 标志位
//    public volatile static boolean flag = true;
//    public static void main(String[] args) throws InterruptedException {
//        new Thread(() -> {
//            try {
//                while (flag) { // 判断是否运行
//                    System.out.println("运行中");
//                    Thread.sleep(1000L);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        // 3 秒之后，将状态标志改为 false，代表不继续运行
//        Thread.sleep(3000L);
//        flag = false;
//        System.out.println("程序运行结束");
//    }
}
