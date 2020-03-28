package cn.offer2020.pbj.javabasis.java.basis2.reflection_10;


import org.junit.Test;

import java.lang.reflect.Method;
//[如何提高使用Java反射的效率？](https://www.cnblogs.com/coding-night/p/10772631.html)
public class TestDemo {

    // 通过普通方式创建TestUser对象
    @Test
    public void testCommon() {
        long start = System.currentTimeMillis();
        TestUser user = null;
        int i = 0;
        while (i < 1000000) {
            ++i;
            user = new TestUser();
        }
        long end = System.currentTimeMillis();
        System.out.println("普通对象创建耗时：" + (end - start) + "ms");
    }

//普通对象创建耗时：10ms


    // 通过反射方式创建TestUser对象
    @Test
    public void testReflexNoCache() throws Exception {
        long start = System.currentTimeMillis();
        TestUser user = null;
        int i = 0;
        while (i < 1000000) {
            ++i;
            user = (TestUser) Class.forName("ReflexDemo.TestUser").newInstance();
        }
        long end = System.currentTimeMillis();
        System.out.println("无缓存反射创建对象耗时：" + (end - start) + "ms");
    }

//无缓存反射创建对象耗时：926ms

    // 通过缓存反射方式创建TestUser对象
    @Test
    public void testReflexWithCache() throws Exception {
        long start = System.currentTimeMillis();
        TestUser user = null;
        Class rUserClass = Class.forName("RefleDemo.TestUser");
        int i = 0;
        while (i < 1000000) {
            ++i;
            user = (TestUser) rUserClass.newInstance();
        }
        long end = System.currentTimeMillis();
        System.out.println("通过缓存反射创建对象耗时：" + (end - start) + "ms");
    }

//通过缓存反射创建对象耗时：41ms

    @Test
    public void testReflexMethod() throws Exception {
        long start = System.currentTimeMillis();
        Class testUserClass = Class.forName("RefleDemo.TestUser");
        TestUser testUser = (TestUser) testUserClass.newInstance();
        Method method = testUserClass.getMethod("sayHi");
        int i = 0;
        while (i < 100000000) {
            ++i;
            method.invoke(testUser);
        }
        long end = System.currentTimeMillis();
        System.out.println("反射调用方法耗时：" + (end - start) + "ms");
    }

//反射调用方法耗时：330ms

    @Test
    public void testReflexMethod2() throws Exception {
        long start = System.currentTimeMillis();
        Class testUserClass = Class.forName("RefleDemo.TestUser");
        TestUser testUser = (TestUser) testUserClass.newInstance();
        Method method = testUserClass.getMethod("sayHi");
        int i = 0;
        while (i < 100000000) {
            ++i;
            method.setAccessible(true);
            method.invoke(testUser);
        }
        long end = System.currentTimeMillis();
        System.out.println("setAccessible=true 反射调用方法耗时：" + (end - start) + "ms");
    }

//setAccessible=true 反射调用方法耗时：188ms
}
