package cn.offer2020.pbj.book_reading.JVM.jvmdemo.oom;

/**
 * @pClassName: StackOFE
 * @author: pengbingjiang
 * @create: 2020/7/19 09:28
 * @description: TODO StackOverFlow VM Options:Xss512k
 * JVM虚拟机栈是有深度的，在执行方法的时候会伴随着入栈和出栈，main方法执行后不停的递归，迟早把栈撑爆了
 */
public class StackOFE {
    public static void stackOverFlowErrorMethod() {
        stackOverFlowErrorMethod();
    }

    public static void main(String[] args) {
        stackOverFlowErrorMethod();
    }
}
