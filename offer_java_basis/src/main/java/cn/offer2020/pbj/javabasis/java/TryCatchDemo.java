package cn.offer2020.pbj.javabasis.java;

import org.junit.Test;

/**
 * @pClassName: TryCatchDemo
 * @author: pengbingjiang
 * @create: 2020/6/28 09:00
 * @description: TODO try-catch-finally 语法块的执行顺序等问题
 * [try、catch、finally语句中有return的各类情况](https://www.cnblogs.com/yychuyu/p/13199329.html)
 */
public class TryCatchDemo {
    public static void main(String[] args) {
        System.out.println("========2=========");
        System.out.println(test2());
        System.out.println("========3=========");
        System.out.println(test3());
        System.out.println("========4=========");
        System.out.println(test4());
        System.out.println("========5=========");
        System.out.println(test5());
        System.out.println("========6=========");
        System.out.println(test6());
    }

    /**
     * @Description: 5.正常有返回值有异常被捕获，finally代码块有返回值，代码结束。
     * @Param: []
     * @return: void
     * @Author: pengbingjiang
     * @Date: 2020/6/28 9:05
     * 输出：try 开始...
     *      catch 捕获到了...
     *      finally 代码块执行完毕...
     *      finally代码块的返回值
     */
    public static String test6() {
        try {
            System.out.println("try 开始...");
            int res = 1 / 0;
            System.out.println("try 结束...");
            return "try代码块的返回值";
        } catch (Exception e) {
            System.out.println("catch 捕获到了...");
            return "catch代码块的返回值";
        }finally {
            System.out.println("finally 代码块执行完毕...");
            return "finally代码块的返回值";
        }
    }

    /**
     * @Description: 5.正常有返回值无异常被捕获，finally代码块有返回值，代码结束。
     * @Param: []
     * @return: void
     * @Author: pengbingjiang
     * @Date: 2020/6/28 9:05
     * 输出：try 开始...
     *      try 结束...
     *      finally 代码块执行完毕...
     *      finally代码块的返回值
     */
    public static String test5() {
        try {
            System.out.println("try 开始...");
            System.out.println("try 结束...");
            return "try代码块的返回值";
        } catch (Exception e) {
            System.out.println("catch 捕获到了...");
            return "catch代码块的返回值";
        }finally {
            System.out.println("finally 代码块执行完毕...");
            return "finally代码块的返回值";
        }
    }

    /**
     * @Description: 4.正常有返回值无异常被捕获。try代码块有返回值，代码结束。finally代码块先于try代码块返回值返回。
     * @Param: []
     * @return: void
     * @Author: pengbingjiang
     * @Date: 2020/6/28 9:05
     * 输出：try 开始...
     *      try 结束...
     *      finally 代码块执行完毕...
     *      try代码块的返回值
     */
    public static String test4() {
        try {
            System.out.println("try 开始...");
            System.out.println("try 结束...");
            return "try代码块的返回值";
        } catch (Exception e) {
            System.out.println("catch 捕获到了...");
            return "catch代码块的返回值";
        }finally {
            System.out.println("finally 代码块执行完毕...");
        }
    }

    /**
     * @Description: 3.正常有返回值异常被捕获，异常之后的代码不会执行。finally代码块先于catch中的返回值返回。
     * @Param: []
     * @return: void
     * @Author: pengbingjiang
     * @Date: 2020/6/28 9:05
     * 输出：try 开始...
     *      catch 捕获到了...
     *      finally 代码块执行完毕...
     *      catch代码块的返回值
     */
    public static String test3() {
        try {
            System.out.println("try 开始...");
            int i = 1 / 0;
            System.out.println("try 结束...");
            return "try代码块的返回值";
        } catch (Exception e) {
            System.out.println("catch 捕获到了...");
            return "catch代码块的返回值";
        }finally {
            System.out.println("finally 代码块执行完毕...");
        }
    }

    /**
     * @Description: 2.正常有返回值异常被捕获，异常之后的代码不会执行。catch中的返回值会返回。
     * @Param: []
     * @return: void
     * @Author: pengbingjiang
     * @Date: 2020/6/28 9:05
     * 输出：try 开始...
     *      catch 捕获到了...
     *      catch代码块的返回值
     */
    public static String test2() {
        try {
            System.out.println("try 开始...");
            int i = 1 / 0;
            System.out.println("try 结束...");
            return "try代码块的返回值";
        } catch (Exception e) {
            System.out.println("catch 捕获到了...");
            return "catch代码块的返回值";
        }
    }

    /**
    * @Description: 1.正常无返回值异常被捕获，异常之后的代码不会执行。
    * @Param: []
    * @return: void
    * @Author: pengbingjiang
    * @Date: 2020/6/28 9:05
     * 输出：try 开始...
     *      Catch 捕获到了...
    */
    @Test
    public static void test1() {
        try {
            System.out.println("try 开始...");
            int i = 1 / 0;
            System.out.println("try 结束...");
        } catch (Exception e) {
            System.out.println("Catch 捕获到了...");
        }
    }
}
