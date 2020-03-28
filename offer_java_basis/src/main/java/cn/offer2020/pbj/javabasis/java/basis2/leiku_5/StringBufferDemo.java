package cn.offer2020.pbj.javabasis.java.basis2.leiku_5;
/**
 * @author PBJ
 *public StringBuffer append(数据类型 变量);
 *String与StringBuffer相互转换--
 * 利用StringBuffer类的构造方法 public StringBuffer(String str)
 * 利用append()方法，public StringBuffer append(String str);
 * StringBuffer类变成String类：toString()方法
 *
 *StringBuffer
 *public StringBuffer reverse()
 *public StringBuffer insert(int offset,)
 *public StringBuffer delete(int start,int end)
 */
public class StringBufferDemo {

	public static void main(String[] args) {
		StringBuffer buf = new StringBuffer();
		buf.append("Hello").append(" ").append("World").append("!");
		change(buf);
		System.out.println(buf);
		StringBuffer buf1 = new StringBuffer("Hello");
		System.out.println(buf1.reverse());
		buf1.insert(0, "lalala ").insert(5, " byebye");
		System.out.println(buf1);
		System.out.println(buf1.delete(6, 11));
	}

	private static void change(StringBuffer temp) {
		temp.append("\n").append("Hello nihao!");
	}

}
