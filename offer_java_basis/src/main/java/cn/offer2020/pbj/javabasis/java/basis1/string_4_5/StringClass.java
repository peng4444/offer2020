package cn.offer2020.pbj.javabasis.java.basis1.string_4_5;

public class StringClass {

	public static void main(String[] args) {
		/*
		 * String类常用方法：字符与字符串
		 * public String(char[] value)  :将字符数组变为String类对象
		 * public String(char[] value,int offset,int count) :将部分字符数组变为String
		 * public char charAt(int index)  :返回指定索引的字符信息
		 * public char [] toCharArray()   :将字符串以字符数组的形式返回
		 * String类常用方法： 字节与字符串
		 * public String (byte[] bytes)  :将全部字节数组变为字符串
		 * public String (byte [] bytes,int offset,int count) :将部分字节数组变为String
		 * public byte[] getBytes() :将字符串变为字节数组
		 * public byte[] getBytesz(String charsetname) throws UnsupportedEncodingException :进行编码转换
		 * String类常用方法：字符串比较
		 * public boolean equals(String anObject) :进行相等判断，区分大小写
		 * public boolean equalsIgnoreCase(String anotherString) ：进行相等比较，不区分大小写
		 * public int compareTo(String anotherString) 判断两个字符串的对象：=0,>0,<0
		 * String类常用方法：字符串查找
		 * public boolean contains(String s) :判断指定父内容是否存在
		 * public int indexOf(Srting str) :由前向后查找指定字符串的位置
		 * public int indexOf(String str ,int fromIndex) :从指定位置开始前向后查找指定字符串的位置
		 * public int lastIndexOf(String str) :由后向前查找指定字符串的位置
		 * public int lastIndexOf(String str,int fromIndex) :从指定位置开始后向前查找指定字符串的位置
		 * public boolean startsWith(String prefix) :判断是否由指定字符串开头
		 * public boolean startsWith(String prefix,int offset) :判断成指定位置开始是否由指定字符串开头
		 * public boolean endsWith(String suffix) :判断是否由指定字符串结尾
		 * String类常用方法：字符串替换
		 * public String replaceAll(String regex,String replacement) :用新的字符串替换旧的内容
		 * public String replaceFirst(String regex,String replacement) :以后首个满足条件的内容
		 * String 类常用方法：字符串截取
		 * public String substring (int brginIndex) :从指定索引截取到结尾
		 * public String substring (int beginIndex,int endIndex) :截取部分子字符串的数据
		 * String 类常用方法： 字符串拆分
		 * public String [] split(String regex) :按照指定字符串进行全部拆分
		 * public String [] split(String repex,int limit) :按照指定字符串进行部分拆分，长度由limit决定
		 * String类其他操作
		 * public String concat(String str)  :字符串连接，与+类似
		 * public String toLowerCase() :转小写
		 * public String toUpperCase() :转大写
		 * public String trim() :去掉字符串左右两边的空格，保留中间的空格
		 * public int length() : 去掉字符串长度
		 * public String intern() :数据入池
		 * public boolean isEmpty() :判断字符串是否为空
		 */

		System.out.println(initcap("AINornNNDION"));
	}


	public static String initcap(String temp){
		return temp.substring(0, 1).toUpperCase() + temp.substring(1).toLowerCase();
	}
}

