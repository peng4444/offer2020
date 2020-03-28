package cn.offer2020.pbj.javabasis.java.basis1.link_13;

/**
 * 链表的基本结构 用户在操作的过程中或许不会关心Node是否存在 所有的结点的引用不应该由用户处理，
 * 应该是一个专门的工具类来进行处理
 * 所有定义一个Link类来帮助客服端去隐藏所有的链表中给出的细节操作
 */
public class Link {
	/**
	 * 对Node对象进行处理,内部类 内部类方便调用(内部类与外部类之间直接进行私有属性的访问)
	 * Node类负责所有的结点数据的保存以及结点关系的匹配
	 */
	class Node {
		private String data;// 结点包含数据的是字符串
		private int val;// 结点包含的数据是整型
		private Node next;// 指向下一个结点

		public Node(String data) {
			this.data = data;
		}

		public void addNode(Node newNode) {
			if (this.next == null) {
				this.next = newNode;
			} else {
				this.next.addNode(newNode);
			}
		}

		public void printNode() {
			System.out.print("-->"+this.data);
			if (this.next != null) {
				this.next.printNode();
			}
		}

		public boolean containsNode(String data) {
			if (data.equals(this.data)) {
				return true;
			} else {
				if (this.next != null) {
					return this.next.containsNode(data);
				} else {
					return false;
				}
			}
		}

		public String getNode(int index) {
			if (Link.this.foot++ == index) {
				return this.data;
			} else {
				return this.next.getNode(index);
			}
		}

		public void setNode(int index, String data) {
			if (Link.this.foot++ == index) {
				this.data = data;
			} else {
				this.next.setNode(index, data);
			}
		}

		public void removeNode(Node previous, String data) {
			if (data.equals(this.data)) {
				previous.next = this.next;
			} else {
				this.next.removeNode(this, data);
			}
		}

		public void toArrayNode() {
			Link.this.retArray[Link.this.foot++] = this.data;
			if (this.next != null) {
				this.next.toArrayNode();
			}
		}

		public int getVal() {
			return val;
		}

		public void setVal(int val) {
			this.val = val;
		}
	}

	// 以上为内部类，内部类方便调用(内部类与外部类之间直接进行私有属性的访问)
	private Node root;
	private int count = 0;
	private int foot = 0;// 表示每一个Node的编号
	private String[] retArray;

	// 添加结点
	public void add(String data) {
		Node newNode = new Node(data);
		if (data == null) {
			return;
		}
		if (this.root == null) {
			this.root = newNode;
		} else {
			this.root.addNode(newNode);
		}
		this.count++;
	}

	// 打印链表
	public void print() {
		if (this.root != null) {
			this.root.printNode();
		}else {
			System.out.println("链表为空");
		}
	}

	// 取得链表添加的长度，删除结点时无法知道，所有不是取得链表长度
	public int size() {
		return this.count;
	}

	// 判断是否为空链表
	public boolean isEmpty() {
		return this.count == 0;
	}

	// 查询链表是否包含某一个结点
	public boolean contains(String data) {
		if (data == null || this.root == null) {
			return false;
		}
		return this.root.containsNode(data);
	}

	// 根据index取得结点
	public String getNode(int index) {
		if (index > this.count) {
			return null;
		}
		this.foot = 0;
		return this.root.getNode(index);
	}

	// 根据index修改结点数据
	public void setNode(int index, String data) {
		if (index > this.count) {
			return;
		}
		this.foot = 0;
		this.root.setNode(index, data);
	}

	// 删除链表数据
	public void remove(String data) {
		if (this.contains(data)) {
			if (data.equals(this.root.data)) {// 删除链表中数据时要判断是不是根节点
				this.root = this.root.next;
			} else {
				this.root.next.removeNode(this.root, data);
			}
			this.count--;
		}
	}

	// 对象数据转换：链表以对象数组的形式返回
	public String[] toArray() {
		if (this.root == null) {
			return null;
		}
		this.foot = 0;
		this.retArray = new String[this.count];
		this.root.toArrayNode();
		return this.retArray;
	}
}
