package nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 两个要注意的点
 * 1.向 Channel 中写入数据前要先 flip()
 * 2.发送信息给服务端后要关闭输出 shutdownOutput();
 * @Author xuwei
 * @Date 2020/4/15
 * @Version V1.0
 **/
public class BlockingClient {
    public static void main(String[] args) throws IOException {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            String request = "你好，我是客户端";
            buffer.put(request.getBytes());

            //发送信息给服务端
            buffer.flip();
            socketChannel.write(buffer);

            socketChannel.shutdownOutput();

            //接收服务端响应
            buffer.clear();

            while (socketChannel.read(buffer) != -1) {
                buffer.flip();
                System.out.println(new String(buffer.array(), 0, buffer.limit()));
                buffer.clear();
            }
        }
    }
}
