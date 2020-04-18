package cn.offer2020.pbj.book_reading.concurrent.chapter19;

import java.util.stream.Stream;

/**
 * @ClassName: StreamDemo
 * @Author: pbj
 * @Date: 2020/4/18 21:40
 * @Description: TODO
 */
public class StreamDemo {
    //Stream接口默认是使用串行的方式，也就是说在一个线程里执行。下面举一个例子：
    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .reduce((a, b) -> {
                    System.out.println(String.format("%s: %d + %d = %d",
                            Thread.currentThread().getName(), a, b, a + b));
                    return a + b;
                })
                .ifPresent(System.out::println);
    }
}
