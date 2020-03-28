package cn.offer2020.pbj.javabasis.java.basis1.link_13;
/**
 * 链表的定义和使用
 * @author PBJ
 * 取出数据第一种
 */
class Node1{
	private String data;
	private Node1 next;
	public Node1(String data) {
		this.data= data;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Node1 getNext() {
		return next;
	}
	public void setNext(Node1 next) {
		this.next = next;
	}
}
public class NodeDemo01 {

	public static void main(String[] args) {
		//第一步：准备数据
		Node1 root = new Node1("火车头");
		Node1 n1 = new Node1("A车厢");
		Node1 n2 = new Node1("B车厢");
		Node1 n3 = new Node1("C车厢");
		root.setNext(n1);
		n1.setNext(n2);
		n2.setNext(n3);
		//第二步：取出数据
		Node1 currentNode = root;//从当前结点开始读取
		while(currentNode!=null) {
			System.out.print(currentNode.getData()+"-->");
			currentNode = currentNode.getNext();
		}
	}
}
