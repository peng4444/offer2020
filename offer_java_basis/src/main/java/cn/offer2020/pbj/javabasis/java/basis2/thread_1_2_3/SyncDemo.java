package cn.offer2020.pbj.javabasis.java.basis2.thread_1_2_3;
class MyThread5 implements Runnable{
	private int ticket = 5;
	/**
	@Override
	public void run() {
		for(int x = 0;x <20;x++) {
			synchronized (this) { //同步代码块
				if(this.ticket>0) {
					try {
						Thread.sleep(100);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"卖票ticket="+this.ticket--);
				}
			}
		}
	}
	**/

	@Override
	public void run() {
		for(int x = 0;x<20;x++) {
			this.sale();
		}
	}
	private synchronized void sale() {//ͬ一共有20张票  同步方法
		if(this.ticket>0) {
			try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"卖票ticket="+this.ticket--);
		}
		
	}
	
}

public class SyncDemo {

	public static void main(String[] args) {
		MyThread5 mt5 = new MyThread5();
		new Thread(mt5,"票贩子A").start();
		new Thread(mt5,"票贩子B").start();
		new Thread(mt5,"票贩子C").start();
		new Thread(mt5,"票贩子D").start();
		new Thread(mt5,"票贩子E").start();
	}

}
