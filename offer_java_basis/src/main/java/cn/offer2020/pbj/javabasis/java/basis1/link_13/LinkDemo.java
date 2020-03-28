package cn.offer2020.pbj.javabasis.java.basis1.link_13;
/**
 * 创建链表
 * add()添加数据
 * print()打印链表数据和结构
 * size()返回链表添加进去的长度
 * contains("Apple")判断是否存在data为A的结点
 * getNode("index")根据index取得链表的数据
 * setNode(index,"data")根据index修改数据为data
 * remove("Apple")删除链表中数据为啊Apple的结点
 * @author PBJ
 *
 */
public class LinkDemo {

	public static void main(String[] args) {
		Link link = new Link();//创建链表
		link.add("First");//add()添加数据
		link.add("Second");
		link.add("Third");
		link.add("Forth");
		link.print();//print()打印链表数据和结构
		System.out.println();
		System.out.println(link.size());//size()返回链表添加进去的长度
		System.out.println(link.contains("Apple"));//contains("Apple")判断是否存在data为A的结点
		link.add("Apple");
		link.print();
		System.out.println();
		//getNode("index")根据index取得链表的数据
		System.out.println(link.getNode(4));//index从0开始
		link.setNode(4, "Fifth");//setNode(index,"data")根据index修改数据为data
		link.print();
		System.out.println();
		link.add("Apple");
		System.out.println("链表长度："+link.size());//size()返回链表添加进去的长度
		link.print();
		System.out.println();
		link.remove("Apple");//remove("Apple")删除链表中数据为啊Apple的结点
		System.out.println("链表长度："+link.size());//size()返回链表添加进去的长度
		link.print();
		System.out.println();
		String [] linkdata = link.toArray();//链表对象数组化
		for(int i=0;i<linkdata.length;i++) {
			System.out.print("---->"+linkdata[i]);
		}
		System.out.println();
	}
}

