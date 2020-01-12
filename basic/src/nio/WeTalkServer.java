package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @Author xuwei
 * @Date 2020-01-06
 * @Version V1.0
 **/
public class WeTalkServer {
    private final int port;

    public WeTalkServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));

        System.out.println("服务端已启动，正在监听 " + port + " 端口......");
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("接受来自" + socketChannel.getRemoteAddress().toString().replace("/", "") + " 请求");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String rec = WeTalkUtil.receiveMsg(socketChannel);

            System.out.println("接收到的信息：" + rec);
            String nextLine = scanner.nextLine();
            if ("exit".equals(nextLine)) {
                break;
            }
            WeTalkUtil.sendMsg(socketChannel, nextLine);
        }
        socketChannel.close();
        serverSocketChannel.close();
    }

    public static void main(String[] args) throws IOException {
        WeTalkServer weTalkServer = new WeTalkServer(8888);
        weTalkServer.start();
    }
}
