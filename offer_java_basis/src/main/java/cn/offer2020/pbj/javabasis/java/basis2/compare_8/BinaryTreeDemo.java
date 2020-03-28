package cn.offer2020.pbj.javabasis.java.basis2.compare_8;
/**
package cn.pbj2.compare;
//javaʵ�ֶ��������룬δ��ɡ�
import java.util.Arrays;

class Book1 implements Comparable<Book1>{
	private String title;
	private double price;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	public Book1(String title,double price) {
		this.title = title;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", price=" + price + "]";
	}
	@Override
	public int compareTo(Book1 o) {
		if(this.price>o.price) {
			return 1;
		}else if(this.price<o.price) {
			return -1;
		}else {
		return 0;
		}
	}
}
@SuppressWarnings("rawtypes")
class BinaryTree{
	private Comparable data;
	private Node left;
	private Node right;
	@SuppressWarnings("unused")
	public Node(Comparable data) {
		this.data = data;
	}
	@SuppressWarnings("unchecked")
	public void addNode(Node newNode) {
		if(this.data.compareTo(newNode.data)>0) {
			if(this.left == null) {
				this.left = newNode;
			}else {
				if(this.right == null) {
					this.left.addNode(newNode);
				}
			}else {
				if(this.right==null) {
					this.right = newNode;
				}else {
					this.right.addNode(newNode);
				}
			}
		}
		public void toArrayNode() {
			if(this.left != null) {
				this.left.toArrayNode();
			}
			BinaryTree.this.retData[BinaryTree.this.foot++] = this.data;
			if(this.right != null) {
				this.right.toArrayNode();
			}
		}
	}
	private Node root;
	private int count;
	private Object [] retData;
	private int foot;
	public void add(Object obj) {
		Comparable com = (Comparable) obj;
		Node newNode = new Node(com);
		if(this.root == null) {
			this.root = newNode;
		}else {
			this.root.addNode(newNode);
		}
		this.count++;
	}
	public Object [] toArray() {
		if(this.root == null) {
			return null;
		}
		this.foot = 0;
		this.retData = new Object[this.count];
		this.root.toArrayNode();
		return this.retData;
	}
}
public class BinaryTreeDemo {

	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		bt.add(new Book1("Java����",88.9));
		bt.add(new Book1("Python����ѧϰ",189.1));
		bt.add(new Book1("Oracle���ݿ�",129.0));
		bt.add(new Book1("�Լ�",120000));
		Object obj[] = bt.toArray();
		System.out.println(Arrays.toString(obj));
		
		
	}

}
**/