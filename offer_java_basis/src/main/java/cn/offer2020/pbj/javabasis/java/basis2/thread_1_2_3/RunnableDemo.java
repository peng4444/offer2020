package cn.offer2020.pbj.javabasis.java.basis2.thread_1_2_3;
class MyThread1 implements Runnable{//实现runnable接口
	private String name;
	public MyThread1(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		for(int x = 0;x< 200;x++) {
			System.out.println(this.name +"--->"+ x);
		}
	}
}

public class RunnableDemo {

	public static void main(String[] args) {
		MyThread1 mt1 = new MyThread1("线程1");
		MyThread1 mt2 = new MyThread1("线程2");
		MyThread1 mt3 = new MyThread1("线程3");
		new Thread(mt1).start();//等同于创建线程  Thread t1 = new Thread(mt1); t1.start();
		new Thread(mt2).start();
		new Thread(mt3).start();
	}

}
