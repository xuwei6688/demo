package nio;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author xuwei
 * @Date 2020/4/12
 * @Version V1.0
 **/
public class Test extends Cat{
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("hello", null);
        Map<String, String> mp = new ConcurrentHashMap<>();
        mp.put("hello", null);
    }
}
