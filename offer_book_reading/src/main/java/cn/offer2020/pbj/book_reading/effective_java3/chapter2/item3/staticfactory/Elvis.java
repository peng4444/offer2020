package cn.offer2020.pbj.book_reading.effective_java3.chapter2.item3.staticfactory;

// Singleton with static factory (Page 17)
public class Elvis {
    //实现单例设计模式（singleton）的两种常见方法

    private static final Elvis INSTANCE = new Elvis();
    private Elvis() { }
    // 2.共有的成员是个静态工厂方法
    public static Elvis getInstance() { return INSTANCE; }

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    // This code would normally appear outside the class!
    public static void main(String[] args) {
        Elvis elvis = Elvis.getInstance();
        elvis.leaveTheBuilding();
    }
}
