package cn.offer2020.pbj.javabasis.java.basis1.reference_7;
class Message2{
	private String info = "nihao";
	public Message2(String info) {
		this.setInfo(info);
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
public class TestDemo3 {

	public static void main(String[] args) {
		Message2 msg = new Message2("Hello");
		fun(msg);
		System.out.println(msg.getInfo());
	}
	public static void fun(Message2 temp) {
		temp.setInfo("World");
	}
}
