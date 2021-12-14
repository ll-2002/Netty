import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class client {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        // ���ӷ�����
        if (!socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888))) {
            while (!socketChannel.finishConnect()) {
                System.out.println("connecting...");
            }
        }
        //��������
        String str = "hello netty";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        socketChannel.write(byteBuffer);
        System.in.read();
    }

}
