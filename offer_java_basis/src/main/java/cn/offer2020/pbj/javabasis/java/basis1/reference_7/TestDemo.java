package cn.offer2020.pbj.javabasis.java.basis1.reference_7;
/*
 * 引用传递的核心要义：同一块堆内存空间可以被不同的栈内存所指向，不同的栈内存可以对同一个堆内存进行内容的修改
 */
class Message{
	private int num = 10;
	public Message(int num) {
		this.setNum(num);
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
public class TestDemo {

	public static void main(String[] args) {
		Message msg = new Message(30);
		fun(msg);
		System.out.println(msg.getNum());
	}
	public static void fun(Message temp) {
		temp.setNum(100);
	}

}
