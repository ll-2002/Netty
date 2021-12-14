import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class client {
    public static void main(String[] args) throws IOException{
        //创建客户端的Socket对象
        //Socket (InetAddress adress,int port)创建流套接字并将其连接到指定IP地址的指定端口号
//		Socket s=new Socket(InetAddress.getByName("192.168.224.1"), 10000);
        //Socket (String host,int port)创建流套接字并将其连接到指定主机的指定端口号
        Socket s=new Socket("127.0.0.1", 50000);

        //获取输出流，写数据
        //OutputStream getOutputStream();返回此套接字的输出流
        OutputStream os=s.getOutputStream();
        os.write("IO!".getBytes());

        //释放资源
        s.close();

    }

}


