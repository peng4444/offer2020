package cn.offer2020.pbj.book_reading.effective_java3.chapter2.item1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Animal {
    //构造方法私有化，客户端不能够使用构造器获得对象
    private Animal(){}

    //根据名称放入相应的实例
    private static final Map<String,IProvider> providers= new ConcurrentHashMap<String,IProvider>();
    public static final String DEFAULT_IPROVIDER_NAME = "<dfe>";

    //用户注册实例的接口 提供者注册API
    public static void registerDefaultProvider(IProvider p){
        registerProvider(DEFAULT_IPROVIDER_NAME,p);
    }

    public static void registerProvider(String name,IProvider p){
        providers.put(name, p);
    }

    //用户获得实例的接口 服务接口
    public static IAnimal newInstance(){
        return newInstance(DEFAULT_IPROVIDER_NAME);
    }

    public static IAnimal newInstance(String name){
        IProvider p = providers.get(name);
        if(p == null)
            throw new IllegalArgumentException(
                    "没有实例注册这个名称:"+name);
        return p.ianimal();
    }
}
