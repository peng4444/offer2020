package cn.offer2020.pbj.javabasis.java.basis2.socket_18;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * �������ࣺserverSocket ��Ҫ�����ڷ������ˣ����ڽ����û�������
 * 
 * @author PBJ
 *���췽����public ServeerSocket(int port)throws IOException
 *���տͻ�������:public Socket accept() throws IOException
 *ȡ�ÿͻ��˵��������:socket�෽��; public OutputStream getOutputStream() throws IOException
 */
public class ServerScoketDemo {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(9999);
		System.out.println("�ȴ��ͻ�������...........");
		Socket client = server.accept();
		PrintStream out = new PrintStream(client.getOutputStream());
		out.println("Hello...");
		out.close();
		client.close();
		server.close();
	}

}
