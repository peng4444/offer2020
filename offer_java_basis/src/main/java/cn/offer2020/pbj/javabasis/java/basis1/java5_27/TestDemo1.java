package cn.offer2020.pbj.javabasis.java.basis1.java5_27;
//Java5新特性，泛型：类在定义的时候，可以使用一个标记，此标记就表示类中属性或者方法参数的类型标记，使用的时候再动态的设置类型
class Point<T>{
	private T x;
	private T y;
	public T getX() {
		return x;
	}
	public void setX(T x) {
		this.x = x;
	}
	public T getY() {
		return y;
	}
	public void setY(T y) {
		this.y = y;
	}
}
public class TestDemo1 {

	public static void main(String[] args) {
		//第一步：设置数据
		Point<String> p = new Point<String>();
		Point<Integer> p1 = new Point<Integer>();
		p.setX("东经100度");
		p.setY("北纬29度");
		p1.setX(22);
		p1.setY(13);
		//第二步：取出数据
		String x = p.getX();
		String y = p.getY();
		System.out.println("x坐标："+x+" y坐标："+y);
		int x1 = p1.getX();
		int y1 = p1.getY();
		System.out.println("x1大小："+x1+" y1大小:"+y1);
	}

}
