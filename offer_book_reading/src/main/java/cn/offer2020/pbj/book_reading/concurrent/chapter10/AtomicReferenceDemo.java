package cn.offer2020.pbj.book_reading.concurrent.chapter10;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName: AtomicReferenceDemo
 * @Author: pbj
 * @Date: 2020/5/20 18:05
 * @Description: TODO 原子引用更新
 */
class User{
    String userName;
    int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }
}
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3 = new User("z3",23);
        User li4 = new User("li4", 24);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, li4)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3, li4)+"\t"+atomicReference.get().toString());
    }


    //时间戳的原子引用 解决ABA问题
    static AtomicReference<Integer> atomicReference1 = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);
    @Test
    public void Demo() {
        new Thread(() ->{
            atomicReference1.compareAndSet(100, 101);
            atomicReference1.compareAndSet(101, 100);
        },"t1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference1.compareAndSet(100,2020)+"\t"+atomicReference1.get());
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("============以下是ABA问题的解决=============");

        new Thread(() ->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第一次版本号："+stamp);
            //暂停3秒t3线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+"\t第二次版本号："+stamp);
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+"\t第三次版本号："+stamp);

        },"t3").start();

        new Thread(() ->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第一次版本号："+stamp);
            //暂停4秒t4线程,保证上面的t3线程也完成一次ABA操作
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean res = atomicStampedReference.compareAndSet(100, 2020, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\t修改是否成功：" + res + "\t当前最新实际版本号：" + atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName()+"\t当前实际最新值："+atomicStampedReference.getReference());
        },"t4").start();
    }
}
