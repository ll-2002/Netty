
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) throws IOException {
        //�����ͻ��˵�Socket����(SevereSocket)
        //ServerSocket (int port)�����󶨵�ָ���˿ڵķ������׽���
        ServerSocket ss=new ServerSocket(50000);

        //Socket accept()����Ҫ���ӵ����׽��ֲ�������
        Socket s=ss.accept();

        //��ȡ�������������ݣ�����������ʾ�ڿ���̨
        InputStream is=s.getInputStream();
        byte[] bys=new byte[1024];
        int len=is.read(bys);
        String data=new String(bys,0,len);
        System.out.println("Hello "+data);

        //�ͷ���Դ
        s.close();
        ss.close();
    }

}


