package cn.offer2020.pbj.book_reading.concurrent.chapter12;

/**
 * @ClassName: TheadLocalMapDemo
 * @Author: pbj
 * @Date: 2020/5/18 16:12
 * @Description: TODO TheadLocalMap代码例子
 * 代码多次创建所占内存非常大的对象，并在创建后，立即解除对象的强引用，让对象可以被JVM回收。按道理来说，
 * 上面的代码运行应该不会发生内存溢出，因为我们虽然创建了多个大对象，占用了大量空间，但是这些对象立即就用不到了，可以被垃圾回收，
 * 而这个对象被垃圾回收后，对象的id，数组，和threadLocal成员都会被回收，所以所占内存不会持续升高，
 */
public class TheadLocalMapDemo {
    public static void main(String[] args) {
        // 循环创建多个TestClass
        for (int i = 0; i < 100; i++) {
            // 创建TestClass对象
            TestClass t = new TestClass(i);
            // 调用反复
            t.printId();
            // 此处调用了ThreadLocal对象的remove方法
            t.threadLocal.remove();
            // *************注意此处，非常关键：为了帮助回收，将t置为null
            t = null;
        }
    }

    static class TestClass {
        int id;
        // 每个TestClass对象对应一个很大的数组
        int[] arr = new int[100000000];
        // 每个TestClass对象对应一个ThreadLocal对象
        ThreadLocal<int[]> threadLocal = new ThreadLocal<>();

        TestClass(int id) {
            this.id = id;
            // threadLocal存放的就是这个很大的数组
            threadLocal.set(arr);
        }

        public void printId() {
            System.out.println(id);
        }
    }
}
