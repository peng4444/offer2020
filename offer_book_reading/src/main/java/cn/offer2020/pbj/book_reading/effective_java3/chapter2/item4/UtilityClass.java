package cn.offer2020.pbj.book_reading.effective_java3.chapter2.item4;

// Noninstantiable utility class (Page 19)
public class UtilityClass {
    // Suppress default constructor for noninstantiability 让一个类包含一个私有构造器，它就不能被实例化
    private UtilityClass() {
        throw new AssertionError();
    }

    // Remainder omitted  由于显式的构造器是私有的，所以不可以在该类的外部访问它。这种习惯用法也有副作用，它使得 个类不能被子类化
}
