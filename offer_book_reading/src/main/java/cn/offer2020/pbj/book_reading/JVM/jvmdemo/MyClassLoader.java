package cn.offer2020.pbj.book_reading.JVM.jvmdemo;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName: MyClassLoader
 * @Author: pbj
 * @Date: 2020/5/28 20:20
 * @Description: TODO 自定义ClassLoader:https://www.cnblogs.com/heartlake/p/12980009.html
 * 同一个类的Class对象在同一个虚拟机进程中，可以存在多个实例，在虚拟机中，是根据Class所属的类加载器，来确定唯一一个Class。
 */
public class MyClassLoader extends ClassLoader {

    public MyClassLoader () {
        super(null);

    }

    @Override
    protected Class findClass (String name) throws ClassNotFoundException {

        String classPath = name.replace(".", "/")+".class";
        InputStream classInputStream = getSystemClassLoader().getResourceAsStream(classPath);
        try {
            byte[] classBytes = new byte[classInputStream.available()];
            classInputStream.read(classBytes);
            Class clazz = defineClass(name, classBytes, 0, classBytes.length);
            resolveClass(clazz);
            return clazz;
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }

    }
}
