package cn.offer2020.pbj.javabasis.java.basis2.socket_18;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * �ͻ�����: Socket ÿһ�����ӷ������ϵ��û���ͨ��Socket��ʾ
 * @author PBJ
 *���췽����public Socket(String host,int port)throws UnkownHostException,IOException
 *host��ʾ������ַ������ֱ�ӷ���ʹ��localhost(127.0.0.1)���IP
 *�õ���������:public InputStream getInputStream() throws IOException
 */
public class SocketDemo {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("localhost",9999);
		Scanner scan = new Scanner(client.getInputStream());
		scan.useDelimiter("\n");
		if(scan.hasNext()) {
			System.out.println("����Ӧ���ݡ���"+scan.next());
		}
		scan.close();
		client.close();

	}

}
