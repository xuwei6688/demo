package gc;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xuwei
 * @Date 2020/7/10
 * @Version V1.0
 **/
public class StopTheWorld {
    public static class MyThread extends Thread {
        Map<Long, byte[]> map = new HashMap<>();
        @Override
        public void run() {
            try {
                while (true) {
                    if (map.size() * 512 / 1024 / 1024 >= 900) {
                        map.clear();
                    }
                    for (int i = 0; i < 100; i++) {
                        map.put(System.nanoTime(), new byte[512]);
                    }
                    Thread.sleep(1);
                }
            } catch (Exception e) {}
        }
    }

    public static class PrintThread extends Thread {
        public static final  long startTime = System.currentTimeMillis();

        @Override
        public void run() {
            try{
                while (true) {
                    long t = System.currentTimeMillis() - startTime;
                    System.out.println(t / 1000 + "." + t % 1000);
                    Thread.sleep(100);
                }
            }catch (Exception e){}
        }
    }

    public static void main(String[] args) {
//        MyThread myThread = new MyThread();
        PrintThread printThread = new PrintThread();
//        myThread.start();
        printThread.start();
    }
}
