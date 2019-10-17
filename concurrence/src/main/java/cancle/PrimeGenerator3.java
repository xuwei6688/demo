package cancle;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 对PrimeGenerator2的改进
 **/
public class PrimeGenerator3 implements Runnable {
    private final BlockingQueue<BigInteger> primes;

    public PrimeGenerator3(BlockingQueue<BigInteger> blockingQueue) {
        this.primes = blockingQueue;
    }

    @Override
    public void run() {
        BigInteger b = BigInteger.ONE;

        try {
            //因为阻塞队列已经检查了中断，所以这里wile条件可以为 true
            //但是这里仍然检查了中断，目的是在素数计算前判断一下，提高响应性
            while (!Thread.currentThread().isInterrupted()) {
                b = b.nextProbablePrime();
                //阻塞队列的put方法会检查中断，如果中断就抛出一个InterruptedException
                primes.put(b);
            }
        } catch (InterruptedException e) {
            System.out.println("hahah");
        }

        while (true) {
            int i = 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<BigInteger> b = new ArrayBlockingQueue<>(10);
        PrimeGenerator3 p = new PrimeGenerator3(b);

        Thread thread = new Thread(p);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } finally {
            thread.interrupt();
        }
        System.out.println(b);

        //BlockingQueue抛出InterruptedException中断状态被重置
        System.out.println(thread.isInterrupted());
    }
}
