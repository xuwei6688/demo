package cancle;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 素数生成器
 * 取消任务 How When What
 * Java并发编程实战 7.1
 **/
public class PrimeGenerator implements Runnable {
    private final List<BigInteger> primes = new ArrayList<>();
    private volatile boolean cancled = false;
    @Override
    public void run() {
        BigInteger bg = BigInteger.ONE;
        while (!cancled) {
            bg = bg.nextProbablePrime();
            synchronized (this) {
                primes.add(bg);
            }
        }
    }

    public void cancle() {
        this.cancled = true;
    }

    public synchronized List<BigInteger> getPrimes() {
        return new ArrayList<>(primes);
    }

    public static void main(String[] args) throws InterruptedException {
        PrimeGenerator p = new PrimeGenerator();
        new Thread(p).start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }finally {
            //在finally中调用cancle确保sleep被中断也可以执行取消
            p.cancle();
        }
        System.out.println(p.getPrimes());
    }
}
