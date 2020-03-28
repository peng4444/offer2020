package cn.offer2020.pbj.javabasis.java.basis2.leiku_5;
/**
 * 数组拷贝：public static void arraycopy(Object src,int srcPos,Object dest,int destPos,int length)
 * 取得当前的系统时间：public static long currentTimeMillis()
 * @author PBJ
 *finalize()方法：protected void finalize() throws Throwable
 *在对象回收时就算抛出了如何异常，也不会影响到整个程序的正常执行
 */
class Member{
	public Member() {
		System.out.println("1.this is a start.");
	}
	protected void finalize() throws Exception {
		System.out.println("2.ok! what do you want to do!");
		throw new Exception("3. we don't know what we can do!");
	}
}

public class SystemDemo {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		String str ="";
		for(int x = 0;x<2000;x++) {
			str = str +x;
		}
		long end = System.currentTimeMillis();
		System.out.println("���β��������ѵ�ʱ�䣺"+(end - start));
		
		System.out.println("-----------------");
		Member mem = new Member();
		mem = null;
		System.gc();
	}

}
