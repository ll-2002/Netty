
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) throws IOException {
        //创建客户端的Socket对象(SevereSocket)
        //ServerSocket (int port)创建绑定到指定端口的服务器套接字
        ServerSocket ss=new ServerSocket(50000);

        //Socket accept()侦听要连接到此套接字并接受他
        Socket s=ss.accept();

        //获取输入流，读数据，并把数据显示在控制台
        InputStream is=s.getInputStream();
        byte[] bys=new byte[1024];
        int len=is.read(bys);
        String data=new String(bys,0,len);
        System.out.println("Hello "+data);

        //释放资源
        s.close();
        ss.close();
    }

}


