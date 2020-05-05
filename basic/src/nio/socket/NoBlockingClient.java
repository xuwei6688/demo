package nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author xuwei
 * @Date 2020/4/15
 * @Version V1.0
 **/
public class NoBlockingClient {
    public static void main(String[] args) throws IOException {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
            socketChannel.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            String request = "你好，我是客户端";
            buffer.put(request.getBytes());

            //发送信息给服务端
            buffer.flip();
            socketChannel.write(buffer);

            socketChannel.shutdownOutput();
        }
    }
}
