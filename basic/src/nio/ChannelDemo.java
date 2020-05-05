package nio;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

/**
 * @Author xuwei
 * @Date 2020-01-02
 * @Version V1.0
 **/
public class ChannelDemo {

    //利用通道完成文件的复制（非直接缓冲区）
    public static void test1() {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try{
            fis = new FileInputStream("1.png");
            fos = new FileOutputStream("2.png");

            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            //从通道中读取内容
            while (inChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                //将缓冲区的内容写入outChannel中
                outChannel.write(byteBuffer);
                //清空缓冲区
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    //零拷贝技术 https://blog.csdn.net/cringkong/article/details/80274148
    //transferFrom transferTo  利用了直接缓冲区
    //fileChannel 的 transferTo 方法底层基于 sendfile64（Linux 平台下）系统调用实现。
    //sendfile64 会直接在内核空间内进行数据拷贝，免去了内核往用户空间拷贝，用户空间再往内核空间拷贝这两步操作
    public static void test2() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("1.png"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.png"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

//        inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());
        outChannel.close();
        inChannel.close();
    }

    //内存映射
    public static void test3() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("1.png"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.png"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        MappedByteBuffer inMapped = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapped = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        byte[] buffer = new byte[inMapped.limit()];
        inMapped.get(buffer);
        outMapped.put(buffer);

        outChannel.close();
        inChannel.close();

    }

    //分散读取与聚集写入
    public static void test4() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("1.txt", "rw");

        //1.获取通道
        FileChannel channel = raf.getChannel();

        //2.创建Buffer
        ByteBuffer buffer1 = ByteBuffer.allocate(100);
        ByteBuffer buffer2 = ByteBuffer.allocate(300);

        ByteBuffer[] buffers = {buffer1, buffer2};

        //3.分散读取
        channel.read(buffers);

        for (ByteBuffer buffer : buffers) {
            buffer.flip();
        }

        System.out.println(new String(buffer1.array(),0, buffer1.limit()));
        System.out.println("-----------");
        System.out.println(new String(buffer2.array(),0, buffer2.limit()));

        //聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();
        channel2.write(buffers);
     }

    public static void main(String[] args) throws IOException {
//        test1();
//        test2();
//        test3();
        test4();
    }
}
