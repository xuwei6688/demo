package nio;

import java.nio.ByteBuffer;

/**
 * @Author xuwei
 * @Date 2019-12-31
 * @Version V1.0
 **/
public class BufferDemo {
    public static void main(String[] args) {
        String str = "abcde";
        System.out.println("---------------分配缓冲区---------------");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println("--------------写---------------");
        buffer.put(str.getBytes());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());


        System.out.println("---------------flip()---------------");
        buffer.flip();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println("---------------get()---------------");
        byte[] bytes = new byte[str.length()];
        buffer.get(bytes, 0, str.length());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println(new String(bytes));

        System.out.println("---------------rewind()---------------");
        buffer.rewind();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println(new String(bytes));

        buffer.clear();


        System.out.println("-----------------clear()----------------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println((char)buffer.get());
    }
}
