package cn.offer2020.pbj.book_reading.concurrent.chapter8;

import org.junit.Test;

/**
 * @ClassName: VolatileReSortSeqDemo
 * @Author: pbj
 * @Date: 2020/5/20 15:35
 * @Description: TODO     // volatile 禁止指令重排
 */
public class VolatileReSortSeqDemo {
    int a = 0;
    boolean flag = false;

    public void method01(){
        a = 1;
        flag = true;
    }

    //多线程环境中线程交替执行，由于编译器优化重排的存在
    // 两个线程中使用的变量能否保证一致性是无法确定的，结果无法预测。
    @Test
    public void method02(){
        if (flag) {
            a = a + 5;
            System.out.println("*****retValue:"+a);
        }
    }
}
