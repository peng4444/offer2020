package cn.offer2020.pbj.javabasis.java.basis2.collection_19;

import java.util.Stack;

public class VectorStack {

	public static void main(String[] args) {
		Stack<String> all = new Stack<String>();
		all.push("A");
		all.push("B");
		all.push("C");
		System.out.println(all.pop());
		System.out.println(all.pop());
		System.out.println(all.pop());
//		System.out.println(all.pop());//EmptyStackException
		all.push("A");
		all.push("B");
		all.push("C");
		System.out.println(all.peek());//查看栈顶元素 Looks at the object at the top of this stack without removing it from the stack.
		System.out.println(all.search("A"));
		System.out.println(all.search("C"));
		//栈的search是从1开始计数
	}

}
