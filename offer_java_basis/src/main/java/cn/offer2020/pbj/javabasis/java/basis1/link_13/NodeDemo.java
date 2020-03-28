package cn.offer2020.pbj.javabasis.java.basis1.link_13;

public class NodeDemo {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Node root = new Node("root");
		Node n1 = new Node("1");
		Node n2 = new Node("2");
		Node n3 = new Node("3");
		root.setNext(n1);
		n1.setNext(n2);
		n2.setNext(n3);
		root.printNode();
	}

}
