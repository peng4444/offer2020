package cn.offer2020.pbj.book_reading.concurrent.chapter8;

/**
 * @ClassName: VolatileExample
 * @Author: pbj
 * @Date: 2020/4/13 10:15
 * @Description: TODO
 */
public class VolatileExample {
    int a = 0;
    volatile  boolean flag = false;

    public void write() {
        a = 1;//step1
        flag = true;//step2
    }

    public void reader() {
        if (flag) {//step3
            System.out.println(a);//step4
        }
    }
}
