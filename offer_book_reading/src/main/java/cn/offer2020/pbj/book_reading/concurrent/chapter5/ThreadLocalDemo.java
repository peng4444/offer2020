package cn.offer2020.pbj.book_reading.concurrent.chapter5;

/**
 * @ClassName: ThreadLocalDemo
 * @Author: pbj
 * @Date: 2020/4/12 16:57
 * @Description: TODO ThreadLocal
 * 最常⻅的ThreadLocal使⽤场景为⽤来解决数据库连接、Session管理等。数据库连接和Session管理涉及多个复杂对象的初始化和关闭。
 */
public class ThreadLocalDemo {

    static class ThreadA implements Runnable {
        private ThreadLocal<String> threadLocal;
        public ThreadA(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal;
        }
        @Override
        public void run() {
            threadLocal.set("A");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadA输出：" + threadLocal.get());
        }
        static class ThreadB implements Runnable {
            private ThreadLocal<String> threadLocal;
            public ThreadB(ThreadLocal<String> threadLocal) {
                this.threadLocal = threadLocal;
            }
            @Override
            public void run() {
                threadLocal.set("B");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ThreadB输出：" + threadLocal.get());
            }
        }
        public static void main(String[] args) {
            ThreadLocal<String> threadLocal = new ThreadLocal<>();
            new Thread(new ThreadA(threadLocal)).start();
            new Thread(new ThreadB(threadLocal)).start();
        }
    }
}
