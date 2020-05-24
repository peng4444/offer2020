package cn.offer2020.pbj.book_reading.JVM.jvmdemo;

/**
 * @ClassName: GCRootDemo
 * @Author: pbj
 * @Date: 2020/5/24 15:09
 * @Description: TODO Java中可以作为GC Roots的对象：
 *               - 虚拟机栈中局部变量表中引用的对象
 *               - 本地方法栈中JNI(Native方法)中引用的对象
 *               - 方法区中类静态属性引用的对象
 *               - 方法区中的常量引用的对象
 */
public class GCRootDemo {
    private byte[] byteArray = new byte[100 * 1024 * 1024];

//    private static GCRootDemo2 t2;//方法区中类静态属性引用的对象
//    private static final GCRootDemo3 t3 = new GCRootDemo3(8);//方法区中的常量引用的对象

    public static void m1() {
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();//虚拟机栈中局部变量表中引用的对象
    }
}
