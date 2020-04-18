package cn.offer2020.pbj.book_reading.concurrent.chapter19;

import java.util.stream.Stream;

/**
 * @ClassName: StreamParallelDemo
 * @Author: pbj
 * @Date: 2020/4/18 21:41
 * @Description: TODO
 */
public class StreamParallelDemo {
    //下面小小地修改一下上面的代码，增加一行代码，使Stream使用多线程来并行计算：
    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .parallel()
                .reduce((a, b) -> {
                    System.out.println(String.format("%s: %d + %d = %d",
                            Thread.currentThread().getName(), a, b, a + b));
                    return a + b;
                })
                .ifPresent(System.out::println);
    }
}
