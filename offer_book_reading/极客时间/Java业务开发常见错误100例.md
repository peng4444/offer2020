# Java开发中的一些常见错误&坏味道
[消灭 Java 代码的“坏味道”](https://developer.aliyun.com/article/718160)
```markdown
范例5：final修饰的对象调用成员变量
class Other{
    public int i;	
}
public class Main{
    public static void void main(String[] args){
        Other o = new Other();
        new Main().addOne();
    }
    public void addOne(final Other o){//在addOne方法中，Other对象被修饰为final
        o.i++;//修改Other对象的成员变量，而o对象的reference并没有改变
    }
}
范例6，7 实例变量（成员变量）有默认值，而final修饰的变量在构造器结束之前必须被赋予一个明确的值。
public class Main{
    int i;
    public void doSomething(){
        System.out.println("i="+i);正常输出i=0,当final int i;时程序会报错。
    }
}
范例8：static方法不能直接调用非static方法，但是可以调用类的非static方法。
------------------------------------------------------------------------------------
Java业务开发常见错误100例
02.代码加锁
    # 代码块级别的synchronized和方法上标记的synchronized关键字，在实现上有什么区别？
    # JDK里的ReentrantLock和ReentrantReadWriteLock都提供了公平锁的版本，但是不要轻易开启，
    在任务很轻的情况下开启公平锁可能会让性能下降上百倍。
03.线程池
    # 为什么禁止使用Java中Executor类中定义的工具方法创建线程池？
    	newFixedThreadPool和newCachedThreadPool可能因为资源耗尽导致OOM问题。
	为什么？
	     1.newFixedThreadPool创建的线程池的工作队列直接new了一个LinkedBlockingQueue,
	     而默认构造方法的LinkedBlockingQueue是一个Integer.MAX_VALUE长度的队列，可以认为是无界的。
	     2.newCacheThreadPool创建的线程池的最大线程数是Integral.MAX_VALUE,可以认为是没有上限的，
	     而其工作队列SynchronousQueue是一个没有存储空间的阻塞队列。这意味着，只要请求到来，
	     就必须找到一条工作线程来处理，如果当前没有空闲的线程就再创建一条新的。
    # 建议手动声明线程池 new ThreadPoolExecutor来创建线程池。还建议用一些监控手段来观察线程池的状态。
    # Java8中的parallel stream功能，可以让我们很方便地并行处理集合中元素，其背后是共享一个ForkJoinPool，默认并行度是CPU核数-1。
	对于CPU绑定任务来说使用这样的配置比较合理，但是如果集合操作涉及到同步IO操作(比如数据库操作，外部服务调用)，
	建议自定义一个ForkJoinPool(或者普通线程池)参考第一讲Demo。
04.连接池
    数据库连接池，Redis连接池，HTTP连接池。
    为了保证连接池正确使用：1.连接池务必确保复用。2.在程序退出之前显式关闭连接池释放资源。
    连接池配置参数：最重要的是最大连接数。
05.HTTP调用
    HTTP调用的超时，重试，并发等等问题。
06.Spring事务
07.数据库索引
08.判等问题
09.数值计算
10.集合类
11.空值处理
12.异常处理：
	1.Spring“统一异常处理”方式是第一个错误： 不在业务代码层面考虑异常处理，仅在框架层面粗犷捕获和处理异常。
	三层架构：
		controller：负责信息收集，参数校验。转换服务层处理的数据适配前端，轻应用逻辑。
		service：负责核心业务逻辑，包括各种外部服务调用，访问数据库，缓存处理，消息处理。
		Respiratory：负责数据访问实现，一般没有业务逻辑。  
	2.小心finally代码块中的资源回收逻辑，确保finally代码块不出现异常。
		AutoCloseable接口的资源，务必使用try-with-resources模式使用资源，确保正常释放。
	3.确保正确处理了线程池中任务的异常。
13.日志
	1.日志框架众多。
	2.配置复杂且容易出错。
	3.日志记录本身就存在一些误区，比如没考虑到日志内容获取的代价，胡乱使用日志级别等等。
14.文件IO
	1.要读写字符流，需要确保文件中字符的字符集和字符流的字符集一致，否则可能产生乱码。
	2.使用files类的一些流式处理操作，注意使用try-with-resources包装stream，确保底层文件资源可以释放。
	3.进行文件字符流操作时，一般不考虑逐字节操作，使用缓冲区进行批量读写减少磁盘IO，性能会提示不少。
15.序列化
16.JDK8日期类

```

