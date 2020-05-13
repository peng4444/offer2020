package cn.offer2020.pbj.book_reading.JVM;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ReentrantLockDemo
 * @Author: pbj
 * @Date: 2020/5/13 18:25
 * @Description: TODO ReentrantLock锁 https://www.cnblogs.com/liuyanling/p/12879178.html
 */
public class ReentrantLockDemo {
    /* *
     * 功能描述: 先写一个简单的demo上手吧，就拿原子性问题中两个线程分别做累加的demo为例，现在使用ReentrantLock来改写：
     * @param: []
     * @return: void
     * @auther: pbj
     * @date: 2020/5/13 18:26
     */
    ReentrantLock reentrantLock = new ReentrantLock();
    int count = 0;
    private void add10K() {
        // 获取锁
        reentrantLock.lock();
        try {
            int idx = 0;
            while (idx++ < 10000) {
                count++;
            }
        } finally {
            // 保证锁能释放
            reentrantLock.unlock();
        }
    }
    //ReentrantLock在这里可以达到和synchronized一样的效果，为了方便你回忆，我再次把synchronized实现互斥的代码贴上
    private synchronized void add10000(){
        int start = 0;
        while (start ++ < 10000){
            this.count ++;
        }
    }
    static void transfer(Account source,Account target, int amt) throws InterruptedException {
        // 锁定转出账户  Thread1锁定了A Thread2锁定了B
        synchronized (source) {
            Thread.sleep(1000);
//            log.info("持有锁{} 等待锁{}",source,target);
            // 锁定转入账户  Thread1需要获取到B,可是被Thread2锁定了。Thread2需要获取到A，可是被Thread1锁定了。所以互相等待、死锁
            synchronized (target) {
                if (source.getBalance() > amt) {
                    source.setBalance(source.getBalance() - amt);
                    target.setBalance(target.getBalance() + amt);
                }
            }
        }
    }

//用ReentrantLock来改造一下死锁代码
//    static void transfer(Account source, Account target, int amt) throws InterruptedException {
//        Boolean isContinue = true;
//        while (isContinue) {
//            if (source.getLock().tryLock()) {
//                log.info("{}已获取锁 time{}", source.getLock(),System.currentTimeMillis());
//                try {
//                    if (target.getLock().tryLock()) {
//                        log.info("{}已获取锁 time{}", target.getLock(),System.currentTimeMillis());
//                        try {
//                            log.info("开始转账操作");
//                            source.setBalance(source.getBalance() - amt);
//                            target.setBalance(target.getBalance() + amt);
//                            log.info("结束转账操作 source{} target{}", source.getBalance(), target.getBalance());
//                            isContinue=false;
//                        } finally {
//                            log.info("{}释放锁 time{}", target.getLock(),System.currentTimeMillis());
//                            target.getLock().unlock();
//                        }
//                    }
//                } finally {
//                    log.info("{}释放锁 time{}", source.getLock(),System.currentTimeMillis());
//                    source.getLock().unlock();
//                }
//            }
//        }
//    }
}
