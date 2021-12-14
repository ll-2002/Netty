import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class server {
    //����ͨ��IO������TCPЭ�飬����������ļ����׽��ֵĿ�ѡ��ͨ����һ�����ڷ���ˣ�
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    /*
     *���������
     */
    public void start(Integer port) throws Exception {
        serverSocketChannel = ServerSocketChannel.open();
        selector = Selector.open();
        //�󶨼����˿�
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        //����Ϊ������ģʽ
        serverSocketChannel.configureBlocking(false);
        //ע�ᵽSelector��
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        startListener();
    }
        private void startListener() throws Exception {
            while (true) {
                // ����ͻ���������select�ķ�������ֵ����Ϊ��
                if (selector.select(1000) == 0) {
                    System.out.println("current not exists task");
                    continue;
                }
                // ������¼������оʹ��ڶ�Ӧͨ����key
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                // �������е�key�ҵ������¼�����ΪAccept��key
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable())
                        handleConnection();
                    if (key.isReadable())
                        handleMsg(key);
                    iterator.remove();
                }
            }
        }
    /**
     * ����������
     */
    private void handleConnection() throws Exception {
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
    }
    /*
     * ������Ϣ
     */
    private void handleMsg(SelectionKey key) throws Exception {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer attachment = (ByteBuffer) key.attachment();
        channel.read(attachment);
        System.out.println("current msg: " + new String(attachment.array()));
    }

    public static void main(String[] args) throws Exception {
       server myServer = new server();
        myServer.start(8888);
    }
}

