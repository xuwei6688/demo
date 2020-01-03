package tools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 **/
public class MonitorTest {
    static class OOMObject{
        public byte[] placeholder = new byte[64 * 1024];
    }
    public static void main(String[] args) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            TimeUnit.MILLISECONDS.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }
}
