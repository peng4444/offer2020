package cn.offer2020.pbj.javabasis.java.basis1.link_13;

import java.util.Stack;

public class NodeLink {
	private int size;//链表节点的个数
	private Node head;//头节点

	public NodeLink() {
		size = 0;
		head = null;
	}

	////链表的每个节点类
	private class Node{
		private Object data;//每个节点的数据
		private Node next;//每个节点指向下一个节点的连接
		private int val;// 结点包含的数据是整型

		public Node(Object data){
			this.data = data;
		}
	}
	//在链表头添加元素
	public Object addHead(Object obj){
		Node newHead = new Node(obj);
		if(size == 0){
			head = newHead;
		}else{
			newHead.next = head;
			head = newHead;
		}
		size++;
		return obj;
	}

	//在链表头删除元素
	public Object deleteHead(){
		Object obj = head.data;
		head = head.next;
		size--;
		return obj;
	}

	//取得链表长度
	public int LinkLength() {
		return size;
	}

	//判断链表是否为空
	public boolean isEmpty(){
		return (size == 0);
	}

	//查找指定元素，找到了返回节点Node，找不到返回null
	public Boolean  find(Object obj){
		Node current = head;
		int tempSize = size;
		while(tempSize > 0){
			if(obj.equals(current.data)){
				return   true;
			}else{
				current = current.next;
			}
			tempSize--;
		}
		return false;
	}

	//删除指定的元素，删除成功返回true
	public boolean delete(Object value){
		if(size == 0){
			return false;
		}
		Node current = head;
		Node previous = head;
		while(current.data != value){
			if(current.next == null){
				return false;
			}else{
				previous = current;
				current = current.next;
			}
		}
		//如果删除的节点是第一个节点
		if(current == head){
			head = current.next;
			size--;
		}else{//删除的节点不是第一个节点
			previous.next = current.next;
			size--;
		}
		return true;
	}


	//显示节点信息
	public void display(){
		if(size >0){
			Node node = head;
			int tempSize = size;
			if(tempSize == 1){//当前链表只有一个节点
				System.out.println("["+node.data+"]");
				return;
			}
			while(tempSize>0){
				if(node.equals(head)){
					System.out.print("["+node.data+"->");
				}else if(node.next == null){
					System.out.print(node.data+"]");
				}else{
					System.out.print(node.data+"->");
				}
				node = node.next;
				tempSize--;
			}
			System.out.println();
		}else{//如果链表一个节点都没有，直接打印[]
			System.out.println("[]");
		}
	}

	/**
	 * 删除链表,从无头单链表中删除节点 由于单链表并没有给出头指针，因此我们无法通过遍历链表的方式找到该节点的前一个节点来改变
	 * 其 next 指向去指向该节点的
	 * next 节点。换一种思路， 我们可以将该节点的元素值全部替换成其 next 节点，然后删除 next 节点，这样就相当于把该节点删除了。
	 */
	public void deleteRandomNode(Node currentNode) {
		Node nextNode = currentNode.next;
		if (nextNode != null) {
			currentNode.data = nextNode.data;
			currentNode.next = nextNode.next;
		}
		nextNode = null;
	}

	/**
	 * 反转链表 给定一个链表的头指针，要求只遍历一次，将单链表中的元素顺序反转过来。
	 * 将head.next插入到head的前面作为头结点，直到head为最后一个结点
	 * @param head
	 * @return
	 */
	public Node ReverseList(Node head) {
		Node pre = null, next = null;
		while (head.next != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	/**
	 * 输入两个链表，找出它们的第一个公共节点
	 * 解法一：为了找到两个链表的公共节点，那么我们可以从尾往头遍历查找，但是只给了我们头节点，因此类似于栈的先进后出，
	 * 因此我们可以用两个栈来保存节点，然后从栈中取出节点进行比较。
	 * 解法二：统计两个链表的长度 len1 和 len2，让较长的链表先走·abs(len1 - len2)`长度，之后二者同时继续往下遍历，查找第一个公共节点。
	 */
	public static Node FindFirstCommonNode1(Node pHead1, Node pHead2) {
		Stack<Node> stack1=new Stack<Node>();
		Stack<Node> stack2=new Stack<Node>();
		Node walkNode=pHead1;
		while(walkNode!=null){
			stack1.push(walkNode);
			walkNode=walkNode.next;
		}
		walkNode=pHead2;
		while(walkNode!=null){
			stack2.push(walkNode);
			walkNode=walkNode.next;
		}
		//当两个链表都为空链表时,无公共结点,返回null
		if(stack1.size()==0||stack2.size()==0){
			return null;
		}
		while(!stack1.empty()&&!stack2.empty()){
			Node pop1=stack1.pop();
			Node pop2=stack2.pop();
			if(pop1!=pop2){
				return pop1.next;
			}
		}
		//当两个栈同时为空时,且没有出现非公共结点,说明两个链表是完全一样的
		if(stack1.size()==0&&stack2.size()==0){
			return pHead1;          //return pHead2;
		}
		//当stack1先空时,说明链表1的所有结点和链表2都是公共的
		else if(stack1.size()==0){
			return pHead1;
		}
		//当stack2先空时,说明链表2的所有结点和链表1都是公共的
		else{
			return pHead2;
		}
	}
	// 取得链表长度
	public int getLength(Node pHead) {
		Node walkNode = pHead;
		int length = 0;
		while (walkNode != null) {
			length++;
			walkNode = walkNode.next;
		}
		return length;
	}

	public Node FindFirstCommonNode2(Node pHead1, Node pHead2) {
		int n1 = getLength(pHead1);
		int n2 = getLength(pHead2);
		int lenDiff = n1 > n2 ? (n1 - n2) : (n2 - n1);
		Node walkNode1 = pHead1;
		Node walkNode2 = pHead2;
		// 将walkNode1与walkNode2对齐
		if (n1 > n2) {
			while (lenDiff > 0) {
				lenDiff--;
				walkNode1 = walkNode1.next;
			}
		} else {
			while (lenDiff > 0) {
				lenDiff--;
				walkNode2 = walkNode2.next;
			}
		}
		// 遍历比较
		while (walkNode1 != null) {
			if (walkNode1 == walkNode2) {
				return walkNode1;//找到了
			}
			walkNode1 = walkNode1.next;
			walkNode2 = walkNode2.next;
		}
		return null;
	}

	/**
	 * 给定两个单链表的头指针，判断这两个链表是否相交。
	 * 由于都是单项链表，也就是都没有环，那么我们可以把第一个链表链接到第二个链表后面，如果新的链表有环，证明了有公共节点。
	 */
	public boolean isIntersect(Node pHead1, Node pHead2) {
		if (pHead1 == null || pHead2 == null) {
			return false;
		}
		while (pHead1.next != null) {
			pHead1 = pHead1.next;
		}
		while (pHead2.next != null) {
			pHead2 = pHead2.next;
		}
		if (pHead1 == pHead2) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isIntersect2(Node pHead1, Node pHead2) {
		if (pHead1 == null || pHead2 == null) {
			return false;
		}
		pHead1.next = pHead2;
		return hasRing(pHead1);
	}

	/**
	 * 给定一个链表，判断这个链表是否存在环 使用追赶的方法，设定两个指针slow、fast，从头指针开始，
	 * 每次分别前进1步、2步。如存在环，则两者相遇；如不存在环，fast遇到NULL退出。
	 */
	public boolean hasRing(Node pHead) {
		boolean hasRing = false;
		Node currentNode = pHead, nextNode = pHead;
		while (currentNode != null && currentNode.next != null) {
			currentNode = currentNode.next.next;
			nextNode = nextNode.next;
			if (currentNode == nextNode) {
				hasRing = true;
			}
		}
		return hasRing;
	}

	/**
	 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null
	 * 碰撞点p到连接点的距离=头指针到连接点的距离，因此，分别从碰撞点、头指针开始走，相遇的那个点就是连接点
	 */
	public Node EntryNodeOfLoop(Node pHead) {
		Node fast = pHead, slow = pHead;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow)
				break;
		}
		if (fast == null || fast.next == null)
			return null;
		fast = pHead;
		while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return fast;
	}

	/**
	 * 如何知道环的长度？ 记录下碰撞点p，slow、fast从该点开始，再次碰撞所走过的操作数就是环的长度s
	 * 带环链表的长度是多少？ 求环链表的人口已经求出连接点距离头指针的长度，加上问题2中求出的环的长度，二者之和就是带环单链表的长度
	 * 给定两个有环链表的头指针，判断这两个链表是否相交。 解法一：对于有环链表，如果相交，存在以下几种情况：
	 */
	public boolean isIntersect3(Node pHead1, Node pHead2) {
		if (pHead1 == null || pHead2 == null) {
			return false;
		}
		Node entry1 = EntryNodeOfLoop(pHead1);
		Node entry2 = EntryNodeOfLoop(pHead2);
		if (entry1 == entry2)
			return true;
		else {
			Node backup = entry2;
			do {
				entry2 = entry2.next;
			} while (entry2 != entry1 && entry2 != backup);
			return entry2 != backup;
		}
	}

	/**
	 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
	 */
	public Node Merge(Node list1, Node list2) {
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}

		Node prev = null;
		Node root = list1.val < list2.val ? list1 : list2;
		while (list1 != null && list2 != null) {
			if (list1.val < list2.val) {
				if (prev == null) {
					prev = list1;
				} else {
					prev.next = list1;
					prev = list1;
				}
				list1 = list1.next;
			} else {
				if (prev == null) {
					prev = list2;
				} else {
					prev.next = list2;
					prev = list2;
				}
				list2 = list2.next;
			}
		}
		while (list1 != null) {
			prev.next = list1;
			prev = list1;
			list1 = list1.next;
		}
		while (list2 != null) {
			prev.next = list2;
			prev = list2;
			list2 = list2.next;
		}

		return root;
	}
}

