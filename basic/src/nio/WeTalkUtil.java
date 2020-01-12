package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author xuwei
 * @Date 2020-01-06
 * @Version V1.0
 **/
public class WeTalkUtil {
    private static int BUFFER_SIZE = 128;
    public static String receiveMsg(SocketChannel socketChannel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        socketChannel.read(buffer);

        buffer.flip();
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);

        return new String(bytes);
    }

    public static void sendMsg(SocketChannel channel, String msg) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        buffer.put(msg.getBytes());

        buffer.flip();
        channel.write(buffer);
    }

}
