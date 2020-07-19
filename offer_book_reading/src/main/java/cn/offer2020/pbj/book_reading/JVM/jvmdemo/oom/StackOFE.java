package cn.offer2020.pbj.book_reading.JVM.jvmdemo.oom;

/**
 * @pClassName: StackOFE
 * @author: pengbingjiang
 * @create: 2020/7/19 09:28
 * @description: TODO StackOverFlow VM Options:Xss512k
 */
public class StackOFE {
    public static void stackOverFlowErrorMethod() {
        stackOverFlowErrorMethod();
    }

    public static void main(String[] args) {
        stackOverFlowErrorMethod();
    }
}
