package cn.offer2020.pbj.javabasis.java.basis1.innerclass_12;

/**
 * @ClassName: InnerDemo1
 * @Author: pbj
 * @Date: 2020/3/25 10:11
 * @Description: TODO 内部类分别有成员内部类、局部内部类、匿名内部类、静态内部类
 * [InnerDemo1:Java内部类的四种分类以及作用](https://www.cnblogs.com/hackerstd/p/12547503.html)
 */
interface outinterface{  //专门用来给局部内部类做向上转型的父接口的操作
}
public class InnerDemo1 {

    // 成员内部类
    innerclass in=new innerclass(); //在成员内部类所在的外类中实例化成员内部类
    public void outf() {
        in.inf();  //因为in是成员内部类的实例化，所以才可以调用
    }
    class innerclass{//成员内部类
        int y=0;
        public innerclass() {//成员内部类的构造方法

        }
        public void inf() {
            System.out.println("内部类方法y="+y);
        }
    }
    //局部内部类
    public outinterface action(String x) {//要把这个类返回出去，就需要通过接口，因为内部类在外部作用域中不存在
        class innerclass2 implements outinterface{
            public innerclass2(String s) {
                s = x;
                System.out.println(s);
            }
        }
        return new innerclass2("do");
    }
    //静态内部类
    static int x=100;
    static class innerclass3 {
        void action() {
            x=1;  //x必须是静态字段
        }
        public static void main(String[] args) {
            System.out.println("我是静态内部类");
        }
    }

    public static void main(String[] args) {
        InnerDemo1 iDemo1=new InnerDemo1();
        iDemo1.outf();

        InnerDemo1.innerclass j= iDemo1.new innerclass();  //非外部类位置成员内部类实例化的方法（即首先要实例化一个外部类）
        InnerDemo1.innerclass k=new InnerDemo1().new innerclass(); //实例化外部类和构造内部类一起写
        j.inf();
    }
}
