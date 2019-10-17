package cancle;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author xuwei
 * @Date 2019/10/16 0016
 * @Version V1.0
 **/
public class PrimeGenerator2 implements Runnable {
    private final BlockingQueue<BigInteger> primes;
    private volatile boolean cancled = false;

    public PrimeGenerator2(BlockingQueue<BigInteger> blockingQueue) {
        this.primes = blockingQueue;
    }

    @Override
    public void run() {
        BigInteger b = BigInteger.ONE;
        while (!cancled) {
            try {
                b = b.nextProbablePrime();
                //由于用了阻塞队列，所以可能阻塞在这里，导致无法取消任务
                primes.put(b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void cancle() {
        this.cancled = true;
    }
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<BigInteger> b = new ArrayBlockingQueue<>(10);
        PrimeGenerator2 p = new PrimeGenerator2(b);

        new Thread(p).start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }finally {
            //在finally中调用cancle确保sleep被中断也可以执行取消
            p.cancle();
        }
        System.out.println(b);
        //一秒后程序不会停止，因为PrimeGenerator2任务没有中断
    }
}
