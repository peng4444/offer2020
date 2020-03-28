package cn.offer2020.pbj.javabasis.java.basis1.java8_31;//package cn.pbj2019.demo.javabasis.basis1.java8_31;
public class InterfacePlus {
    /**
     * Java8新特性，接口增强，使用default与static在接口中定义方法
     * 允许在接口里面定义普通方法
     * 允许在接口里面定义static方法，直接调用接口static方法使用
     * Java允许内部类访问方法参数的时候开业不加上final关键字
     * @param args
     * 避免接口需要增加方法时，需要覆写所有的子类
     * 接口的使用还是应该以抽象方法为主
     */
    public static void main(String[] args) {
        IMessage msg = new MessageImpl();
        msg.fun();
        System.out.println("-----------");
        IMessage.get();//虽然有方法继承了接口，但是没有用啊。
    }
}
interface IMessage{
    public void print();
    default void fun() {//default定义接口方法
        System.out.println("接口里面的方法");
    }
    static void get() {//接口自己调用接口里面的static方法
        System.out.println("static接口方法调用");
    }
}
class MessageImpl implements IMessage{

    @Override
    public void print() {
        System.out.println("Hello World");
    }
}
