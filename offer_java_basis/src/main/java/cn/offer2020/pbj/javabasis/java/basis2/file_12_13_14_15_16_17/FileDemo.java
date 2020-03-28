package cn.offer2020.pbj.javabasis.java.basis2.file_12_13_14_15_16_17;



import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 文件操作类：Java.io包中包括五个核心类:File InputStream OutputStream Reader Writer  一个核心接口:Serializable
 * @author PBJ
 *
 */
public class FileDemo {

	public static void main(String[] args) throws IOException {
		/**
		 * File类
		 * 设置完整路径: public File(String pathname)
		 * 设置父路径与子文件路径:public File(File parent,String child)
		 * 创建文件:public boolean createNewFile() throws IOException
		 * 删除文件:public boolean delete()
		 * 判断文件是否存在:public boolean exists()
		 */
		File file = new File("f:\\test.txt");//设置文件内容,Windows 使用 "\" ,Linux 使用"/"
		//File fiel = new File("f:"+File.separetor+"test.txt");//统一，通用
		if(file.exists()) {
			file.delete();
		}else {
			System.out.println(file.createNewFile());
		}
		/*
		 * 找到父路径:public File getParentFile()
		 * 创建目录:  public boolean mkdirs()
		 */
		File file2 = new File("f:"+File.separator+"demo"+File.separator+"test.txt");//创建子目录文件夹
		if(!file2.getParentFile().exists()) {
			file2.getParentFile().mkdirs();
		}
		if(file2.exists()) {
			file2.delete();
		}else {
			System.out.println(file2.createNewFile());
		}
		/**
		 * 取得文件大小：public long length()
		 * 判断是否是文件: public boolean isFile()
		 * 判断是否是目录: public boolean idDirectory()
		 * 最近一次修改时间：public long lastModified()
		 */
		if(file2.exists()) {
			System.out.println(file2.isFile());
			System.out.println(file2.isDirectory());
			System.out.println(new BigDecimal((double) file.length()/1024/1024).divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_UP)+"M");
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(new Date(file2.lastModified())));
		}
		/**
		 * 列出来目录下的信息：public String []lsit()
		 * 列出所有的信息以File类对象包装:publie File[] listFiles()
		 */
		if(file.exists()) {
			String result[] = file.list();
			for(int x= 0;x<result.length;x++) {
				System.out.println(result[x]);
			}
		}
		if(file.isDirectory()) {
			File result1 [] = file.listFiles();
			for(int x= 0;x<result1.length;x++) {
				System.out.println(result1[x]);
			}
		}
	}
	public void FileResource() {//文件的一个资源管理器
		File file = new File("E:"+File.separator+"DemoTime"+File.separator+"test.txt");
		if(!file.exists()){
			System.out.println("file not find,please check it!");
		}
		if(file.isDirectory()) {//是一个路径
			File result[] = file.listFiles();
			for(int x= 0; x<result.length;x++) {
				System.out.println(
						result[x].getName()+"\t\t\t\t"
								+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(new Date(result[x].lastModified()))
								+ "\t\t"
								+ (result[x].isDirectory() ? "文件夹" : "文件")
								+ "\t\t"
								+ (result[x].isFile() ? (new BigDecimal((double)result[x].length()/1024/1024).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP)):""));
			}
		}
	}
	//列出指定目录下的所有子路径
	public static void print(File file) {
		if(file.isDirectory()) {
			File result [] = file.listFiles();//列出子目录
			if (result!=null) {
				for(int x = 0;x<result.length;x++) {
					print(result[x]);
				}
			}
		}
		System.out.println(file);
	}
}
