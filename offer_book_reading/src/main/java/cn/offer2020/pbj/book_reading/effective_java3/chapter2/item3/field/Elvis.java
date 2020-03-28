package cn.offer2020.pbj.book_reading.effective_java3.chapter2.item3.field;

// Singleton with public final field  (Page 17)
public class Elvis {
    //实现单例设计模式（singleton）的两种常见方法
    // 1.共有静态成员是一个final域
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() { }

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    // This code would normally appear outside the class!
    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();
    }
}