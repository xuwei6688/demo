import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author xuwei
 * @Date 2020/2/2
 * @Version V1.0
 **/
public class CpuTest {

    static {

    }
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        new Thread( ()->{
            while (true) {
                int a = 9;
                int b = a + 89;
            }
        }, "thread1").start();
    }




}
