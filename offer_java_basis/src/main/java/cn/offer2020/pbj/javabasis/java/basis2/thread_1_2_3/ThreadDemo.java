package cn.offer2020.pbj.javabasis.java.basis2.thread_1_2_3;

class MyThread extends Thread{//继承Thread类
	private String name;
	public MyThread(String name ) {
		this.name = name;
	}
	public void run() {
		for(int x = 0;x < 200;x ++) {
			System.out.println(this.name + "-->" + x);
		}
	}
}

public class ThreadDemo {

	public static void main(String[] args) {
		MyThread mt1 = new MyThread("线程1");
		MyThread mt2 = new MyThread("线程2");
		MyThread mt3 = new MyThread("线程3");
		mt1.start();
		mt2.start();
		mt3.start();
	}
}
