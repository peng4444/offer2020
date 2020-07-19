package cn.offer2020.pbj.book_reading.JVM.jvmdemo.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @pClassName: MetaspaceOOM
 * @author: pengbingjiang
 * @create: 2020/7/19 09:30
 * @description: TODO 元数据区域溢出 VM options：-XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=30M。
 * 通过CBLIG大量生成类，导致Meta信息满了；JDK7的时候使用String.intern()不当，会产生大量常量数据；加载大量的jsp以及动态生成jsp文件。
 * 我们需要调整元数据空间的大小，如果调大了之后还出现了这种异常，我们需要分析哪里出现的溢出并fix掉。
 */
public class MetaspaceOOM {
    static class OOMObject{}

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method,
                                        Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            enhancer.create();
        }
    }
}
