package cn.offer2020.pbj.javabasis.java.basis2.leiku_5;

import java.io.IOException;

/**
 * public long totalMemory()返回所有的可用内存空间
 * public long maxMemory() 返回最大可用的内存空间
 * public long freeMemory() 返回空闲内存空间
 *
 * @author PBJ
 *ִ执行程序 public Process exec(String command) throws IOException
 */
public class RunntimeDemo {

	public static void main(String[] args) throws IOException, InterruptedException {
		Runtime run = Runtime.getRuntime();
		System.out.println("1,Max="+run.maxMemory());
		System.out.println("1,Total="+run.totalMemory());
		System.out.println("1,Free="+run.freeMemory());
		String str ="";
		for(int x = 0;x<2000;x++) {
			str = str +x;
		}
		System.out.println("2,Max="+run.maxMemory());
		System.out.println("2,Total="+run.totalMemory());
		System.out.println("2,Free="+run.freeMemory());
		run.gc();
		System.out.println("3,Max="+run.maxMemory());
		System.out.println("3,Total="+run.totalMemory());
		System.out.println("3,Free="+run.freeMemory());
		
		Runtime run1 = Runtime.getRuntime();
		Process pro = run1.exec("mspaint.exe");
		Thread.sleep(2000);
		pro.destroy();
	}

}
