package cn.offer2020.pbj.book_reading.JVM.jvmdemo;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @ClassName: WeakHashMapDemo
 * @Author: pbj
 * @Date: 2020/5/24 19:54
 * @Description: TODO WeakHashMap和HashMap的区别
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();
        System.out.println("======================");
        myWeakHashMap();
    }

    public static void myHashMap() {
        HashMap<Integer,String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map+" "+map.size());
    }

    public static void myWeakHashMap() {
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(1);
        String value = "WeakHashMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map+" "+map.size());
    }
}
