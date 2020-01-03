package nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
    //transferFrom transferTo  利用了直接缓冲区
    //ileChannel 的 transferTo 方法底层基于 sendfile64（Linux 平台下）系统调用实现。
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

    public static void main(String[] args) throws IOException {
//        test1();
//        test2();
        test3();
    }
}
