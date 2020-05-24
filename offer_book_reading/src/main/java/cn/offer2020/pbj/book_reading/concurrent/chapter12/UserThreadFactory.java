package cn.offer2020.pbj.book_reading.concurrent.chapter12;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: UserThreadFactory
 * @Author: pbj
 * @Date: 2020/5/24 10:00
 * @Description: TODO 自定义线程工厂，并且根据外部特征进行分组。
 */
public class UserThreadFactory implements ThreadFactory {

    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);

    UserThreadFactory(String whatFeatureOfGroup) {
        namePrefix = "From UserThreadFactory's " + whatFeatureOfGroup + "-Worker-";
    }
    @Override
    public Thread newThread(Runnable task) {
        String name = namePrefix +nextId.getAndIncrement();
        Thread thread = new Thread(null,task,name,0);
        System.out.println(thread.getName());
        return thread;
    }
}
