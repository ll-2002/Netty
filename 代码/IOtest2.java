import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class client {
    public static void main(String[] args) throws IOException{
        //�����ͻ��˵�Socket����
        //Socket (InetAddress adress,int port)�������׽��ֲ��������ӵ�ָ��IP��ַ��ָ���˿ں�
//		Socket s=new Socket(InetAddress.getByName("192.168.224.1"), 10000);
        //Socket (String host,int port)�������׽��ֲ��������ӵ�ָ��������ָ���˿ں�
        Socket s=new Socket("127.0.0.1", 50000);

        //��ȡ�������д����
        //OutputStream getOutputStream();���ش��׽��ֵ������
        OutputStream os=s.getOutputStream();
        os.write("IO!".getBytes());

        //�ͷ���Դ
        s.close();

    }

}


