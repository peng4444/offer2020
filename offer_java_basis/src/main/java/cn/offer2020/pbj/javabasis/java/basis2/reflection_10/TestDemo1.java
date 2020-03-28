package cn.offer2020.pbj.javabasis.java.basis2.reflection_10;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @ClassName: TestDemo1
 * @Author: pbj
 * @Date: 2020/1/31 15:08
 * @Description: TODO
 * [Java反射的常见用法](https://www.cnblogs.com/JavaArchitect/p/12244680.html)
 */
public class TestDemo1 {

    /* *
     * 功能描述: 1.反射根据类文件看到指定类的属性，比如属性的修饰符，属性的类型，属性名称
     * @param: []
     * @return: void
     * @auther: pbj
     * @date: 2020/1/31 15:25
     */
    @Test
    public void getFieldsInfo() {
        Class<TestUser> clazz = TestUser.class;

        Field[] fields = clazz.getDeclaredFields();//获取这个类的所有的属性

        for (Field field : fields) {
            System.out.print(Modifier.toString(field.getModifiers()) + "\t");//获取修饰符
            System.out.print(field.getGenericType().toString() + "\t");//获取属性的类型
            System.out.print(field.getName());//获取属性的名称
            System.out.println();
        }
    }

    /* *
     * 功能描述: 2.查看方法的返回类型，参数，和名字
     * @param: []
     * @return: void
     * @auther: pbj
     * @date: 2020/1/31 15:33
     */
    @Test
    public void getMethodIfo() {
        Class<TestUser> clazz = TestUser.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.print(method);
        }
        //得到所有的构造函数
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        //输出所有的构造函数
        for (Constructor constructor : declaredConstructors) {
            System.out.println(constructor);
        }
    }

    //3.通过forName和newInstance方法加载类
    public void addField() {
        //通过new的方式创建类和使用类的方式
        TestUser testUser = new TestUser();
        testUser.sayHi();
        //通过forName和newInstance加载类的方式
        try {
            Class<?> clazz = Class.forName("TestUser");
            TestUser testUser1 = (TestUser) clazz.newInstance();
            testUser1.sayHi();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //4.通过反射机制调用类的方法
    @Test
    public void reflectDemo() {
        Class clazz = null;
        Constructor constructor = null;
        try {
            clazz = Class.forName("TestUser");
            Method sayHi = clazz.getMethod("sayHi", String.class);
//            sayHi.invoke();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
