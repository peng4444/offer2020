package cn.offer2020.pbj.javabasis.other.rpc;
//����˽ӿ�ʵ��
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String ping) {
        return ping != null ? ping +" --> I am ok." : "I am ok.";
    }
}
