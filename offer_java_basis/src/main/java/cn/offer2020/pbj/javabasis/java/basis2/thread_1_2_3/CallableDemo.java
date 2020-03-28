package cn.offer2020.pbj.javabasis.java.basis2.thread_1_2_3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread2 implements Callable<String>{//实现Callable接口
	private int ticket = 10;
	@Override
	public String call() throws Exception {
		for(int x = 0;x <100;x++) {
			if(this.ticket >0) {
				System.out.println("卖票,tickey = "+ this.ticket --);
			}
		}
		return "票已卖光!";
	}
}

public class CallableDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		MyThread2 mt1 = new MyThread2();
		MyThread2 mt2 = new MyThread2();
		FutureTask<String> task1 = new FutureTask<String>(mt1);
		FutureTask<String> task2 = new FutureTask<String>(mt2);
		new Thread(task1).start();
		new Thread(task2).start();
		System.out.println("A线程的返回结果"+task1.get());
		System.out.println("B线程的返回结果"+task2.get());

	}

}
