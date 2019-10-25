package util;

import java.util.concurrent.TimeUnit;

/**
 * @Author xuwei
 * @Date 2019/10/17 0017
 * @Version V1.0
 **/
public class SleepUtil {
    public static void sleep(int timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void milSleep(int timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
