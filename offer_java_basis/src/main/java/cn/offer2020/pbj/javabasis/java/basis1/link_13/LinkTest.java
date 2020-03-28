package cn.offer2020.pbj.javabasis.java.basis1.link_13;


public class LinkTest {
	public void testSingleLinkedList(){
		SingleLinkedList singleList = new SingleLinkedList();
		singleList.addHead("A");
		singleList.addHead("B");
		singleList.addHead("C");
		singleList.addHead("D");//单链表只能在表头添加数据
		//打印当前链表信息
		singleList.display();
		//删除C
		singleList.delete("C");
		singleList.display();
		//单链表在表头删除元素
		System.out.println("---删除表头----");//那不是努力的是、都可以删除
		singleList.delete("B");
		singleList.display();
		//查找B
		System.out.println("链表结点数据是否存在："+singleList.find("Q"));
		//取得链表长度
		System.out.println("链表长度："+singleList.LinkLength());
		//判断链表是否为空
		System.out.println("链表是否为空："+singleList.isEmpty());

	}

	//stack : LIFO 后进先出
	public void TestStackSingleLink() {
		StackSingleLink stackSL = new StackSingleLink();
		stackSL.push("A");
		stackSL.push("B");
		stackSL.push("C");
		stackSL.display();
		stackSL.pop();
		stackSL.display();
		System.out.println("栈是否为空："+stackSL.isEmpty());
	}

	public void TestDoublePointLinkedList(){
		DoublePointLinkedList doublePL = new DoublePointLinkedList();

		doublePL.addHead("A");
		doublePL.addHead("B");
		doublePL .addHead("C");
		doublePL .addHead("Head");
		doublePL.display();
		doublePL.addTail("Tail");
		doublePL.display();
		System.out.println(doublePL.deleteHead());
		doublePL.display();

	}

	//队列 FIFO先进先出
	public void TestQueneLinkedList() {
		QueueLinkedList queneLL = new QueueLinkedList();
		System.out.println(queneLL.isEmpty());
		queneLL.insert("A");
		queneLL.insert("B");
		queneLL.insert("C");
		queneLL.display();
		System.out.println(queneLL.getSize());
		queneLL.delete();
		queneLL.display();
	}

	//有序链表
	public void TestOrderLinkedList() {
		OrderLinkedList orderLL = new OrderLinkedList();
		orderLL.insert(10);
		orderLL.insert(1);
		orderLL.insert(22);
		orderLL.display();
		orderLL.deleteHead();
		orderLL.display();
		orderLL.insert(3);
		orderLL.display();
	}

	//双向链表
	public void TestTwoWayLinkedList() {
		TwoWayLinkedList twowayLL = new TwoWayLinkedList();
		twowayLL.addHead("A");
		twowayLL.addHead("B");
		twowayLL.addHead("C");
		twowayLL.addHead("Head");
		twowayLL.display();
		twowayLL.addTail("Tail");
		twowayLL.display();
		System.out.println("链表长度："+twowayLL.getSize());
		twowayLL.deleteHead();
		twowayLL.display();
		twowayLL.deleteTail();
		twowayLL.display();
		System.out.println("链表是否为空："+twowayLL.isEmpty());
	}
}
