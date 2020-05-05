package nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author xuwei
 * @Date 2020/4/15
 * @Version V1.0
 **/
public class BlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));

        while (true) {
            try (SocketChannel socketChannel = serverSocketChannel.accept()) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                //接收请求数据
                while (socketChannel.read(buffer) != -1) {
                    buffer.flip();
                    System.out.println(new String(buffer.array(), 0 , buffer.limit()));
                    buffer.clear();
                }

                //响应数据给客户端
                String response = "服务端接收到数据";
                buffer.put(response.getBytes());
                buffer.flip();
                socketChannel.write(buffer);
            }
        }
    }
}
