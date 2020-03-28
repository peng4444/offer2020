package cn.offer2020.pbj.javabasis.java.basis1.other_21;
/*
 * 匿名内部类是在接口和抽象类的基础上的发展，匿名内部类的最大好处是帮助用户减少类的定义
 */
interface Message{
	public void print();
}
class MessageImpl implements Message{
	public void print() {
		System.out.println("Hello /world");
	}
}
public class NinnerDmeo {

	public static void main(String[] args) {
		fun(new MessageImpl());
	}
	public static void fun(Message msg) {
		msg.print();
	}
}
