package nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @Author xuwei
 * @Date 2020/4/15
 * @Version V1.0
 **/
public class NonBlockingServer {
    public static void main(String[] args) throws IOException {
        //1.获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //2.切换到非阻塞模式
        serverSocketChannel.configureBlocking(false);

        //3.绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9999));

        //4.获取选择器
        Selector selector = Selector.open();

        //5.将通道注册到选择器上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //6.轮询准备就绪事件
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                //7.获取就绪的事件
                SelectionKey sk = iterator.next();
                //8.判断是什么事件就绪
                if (sk.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();

                    //切换到非阻塞模式
                    socketChannel.configureBlocking(false);

                    //注册到选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    //获取当前选择器上读就绪的通道
                    SocketChannel socketChannel = (SocketChannel) sk.channel();

                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (socketChannel.read(buffer) != -1) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0 , buffer.limit()));
                        buffer.clear();
                    }
                }
            }
            iterator.remove();
        }
    }
}
