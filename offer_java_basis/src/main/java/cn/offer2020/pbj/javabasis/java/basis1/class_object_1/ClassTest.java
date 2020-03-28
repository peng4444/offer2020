package cn.offer2020.pbj.javabasis.java.basis1.class_object_1;

class Book{
	String title;
	double price;
	public void getInfo() {
		System.out.println("Titile:"+title+" Price:"+price);
	}
}

public class ClassTest {

	public static void main(String[] args) {
		/*
		 * 对象的定义
		 * 1.声明并实例化对象：类名称 对象名称  = new 类名称();
		 * 2. 分步完成
		 * 声明对象： 类名称 对象名称  = null;
		 * 实例化对象：对象名称  = new 类名称();
		 */
		Book bk = new Book();
		Book bk2 = null;
		Book bk3 = new Book();
		/*2
		 * Book bk = null;
		 * bk = new Book();
		 */
		/*
		 * 对象.属性：表示要操作的类中的属性内容
		 * 对象.方法()：表示要调用类中的方法
		 */
		bk.title = "Java";
		bk.price = 88.8;
		bk.getInfo();//Titile:Java Price:88.8
		bk2 = bk;//引用传递，bk bk2 都指向同一个堆内存空间
		bk2.getInfo();//Titile:Java Price:88.8
		//一块堆内存可以同时被多个栈内存所指向，但是一个堆内存只能保存一个堆内存的空间地址
		bk3.title = "MySQL";
		bk3.price = 66.6;
		bk3.getInfo();//Titile:MySQL Price:66.6
		bk3.price = 44.4;
		bk3.getInfo();//Titile:MySQL Price:44.4
		/*
		 * 在Java中，这些基本类型有：boolean、char、byte、short、int、long、float、double和void；
		 * 还有与之对应的包装器：Boolean、Character、Byte、Short、Integer、Long、Float、Double和Void
		 */
		//基本类型变量直接存储值，并置于栈中，因此更加高效。
		int a = 3;
		int b = 4;
		System.out.println(a);
		System.out.println(b);
		a = 5;
		System.out.println(a);//基本数据类型存储在栈内存
		/*
		 * 基本数据类型和引用数据类型的区别
		 * 区别；
		 * 1、内存使用：基本数据类型的值存储在栈内存，引用数据类型的值存储在堆内存，把首地址存储在栈内存
		 * 2、变量赋值：基本数据类型把值复制给新变量，引用数据类型把首地址复制给新变量。
		 */
		/*
		 * java的堆是一个运行时数据区，类的对象从中分配空间。这些对象通过new、newarray、anewarray、multianewarray等指令建立，
		 * 它们不需要程序代码来显式地释放。堆是由垃圾回收器来负责的，
		 * 堆的优势是可以动态地分配内存大小，生存期也不必事先告诉编译器，因为它是在运行时动态分配内存的，
		 * java的垃圾回收器会自动收走这些不再使用的数据。但缺点是，由于要在运行时动态分配内存，存取速度较慢。
		 * 
		 * 栈的优势是，存取速度比堆要快，仅次于寄存器，栈数据可以共享。
		 * 但缺点是，存在栈中的数据大小与生存期必须是确定的，缺乏灵活性。
		 * 栈中主要存放一些基本类型的变量（byte、short、int、long、float、double、char、boolean）和对象句柄（引用变量）。
		 */
		
		String A = "abc";
		String B = "abc";
		String c = new String("abc");
		String d = c.intern();//intern()方法,手工入池，设计的初衷，就是重用String对象，以节省内存消耗。
		//A B c d 是同一个内存地址，指向
		System.out.println(A.hashCode());
		System.out.println(B.hashCode());
		System.out.println(c.hashCode());
		System.out.println(d.hashCode());
	}
	
}
