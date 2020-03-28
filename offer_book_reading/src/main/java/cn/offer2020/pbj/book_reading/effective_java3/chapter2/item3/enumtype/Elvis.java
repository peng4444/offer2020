package cn.offer2020.pbj.book_reading.effective_java3.chapter2.item3.enumtype;

// Enum singleton - the preferred approach (Page 18)
//JDK1.5之后出现了枚举，并且完美支持单例模式，并且线程安全、效率高！但是这些不是最重要的，
//    最重要的是书写超级简单！究竟有多简单，看下面的示例应该就可以了解
public enum Elvis {
    INSTANCE;

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    // This code would normally appear outside the class!
    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();
    }
}
