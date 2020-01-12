package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @Author xuwei
 * @Date 2020-01-06
 * @Version V1.0
 **/
public class WeTalkClient {
    private final int port;

    public WeTalkClient(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8888));
        System.out.println("客户端已启动");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入：");

            String msg = scanner.nextLine();
            if ("exit".equals(msg)) {
                break;
            }
            WeTalkUtil.sendMsg(socketChannel, msg);

            String rec = WeTalkUtil.receiveMsg(socketChannel);
            System.out.println("接收到的消息：" + rec);
        }
        socketChannel.close();
    }

    public static void main(String[] args) throws IOException {
        WeTalkClient weTalkClient = new WeTalkClient(555);
        weTalkClient.start();
    }
}
