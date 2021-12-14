import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class server {
    //网络通信IO操作，TCP协议，针对面向流的监听套接字的可选择通道（一般用于服务端）
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    /*
     *开启服务端
     */
    public void start(Integer port) throws Exception {
        serverSocketChannel = ServerSocketChannel.open();
        selector = Selector.open();
        //绑定监听端口
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        //设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //注册到Selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        startListener();
    }
        private void startListener() throws Exception {
            while (true) {
                // 如果客户端有请求select的方法返回值将不为零
                if (selector.select(1000) == 0) {
                    System.out.println("current not exists task");
                    continue;
                }
                // 如果有事件集合中就存在对应通道的key
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                // 遍历所有的key找到其中事件类型为Accept的key
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
     * 处理建立连接
     */
    private void handleConnection() throws Exception {
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
    }
    /*
     * 接收信息
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

