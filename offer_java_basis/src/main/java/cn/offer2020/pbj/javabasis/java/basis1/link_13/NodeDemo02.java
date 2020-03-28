package cn.offer2020.pbj.javabasis.java.basis1.link_13;
/**
 * 链表的定义和使用
 * @author PBJ
 * 取出数据第二种
 */
class Node2{
	private String data;
	private Node2 next;
	public Node2(String data) {
		this.data= data;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Node2 getNext() {
		return next;
	}
	public void setNext(Node2 next) {
		this.next = next;
	}
}
public class NodeDemo02 {

	public static void main(String[] args) {
		//第一步：准备数据
		Node2 root = new Node2("火车头");
		Node2 n1 = new Node2("A车厢");
		Node2 n2 = new Node2("B车厢");
		Node2 n3 = new Node2("C车厢");
		root.setNext(n1);
		n1.setNext(n2);
		n2.setNext(n3);
		print(root);		//第二步：取出数据
	}

	private static void print(Node2 currentNode) {
		if(currentNode==null) {
			return;
		}
		System.out.println(currentNode.getData());
		print(currentNode.getNext());
	}
}

