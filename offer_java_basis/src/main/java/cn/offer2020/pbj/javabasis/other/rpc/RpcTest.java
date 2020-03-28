package cn.offer2020.pbj.javabasis.other.rpc;

import java.net.InetSocketAddress;

/**
 * Javaʵ�ּ򵥵�RPC���(��������) https://www.cnblogs.com/aspirant/p/8631359.html
 */
public class RpcTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    RpcExporter.exporter("localhost",8088);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        RpcImporter<EchoService> importer = new RpcImporter<EchoService>();
        EchoService echo = importer.importer(EchoServiceImpl.class,
                new InetSocketAddress("localhost", 8088));
        System.out.println(echo.echo("Are you ok?"));
    }
}
