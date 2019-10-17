package memorizer;

import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *  模拟一个十分耗时的操作
 * @Author xuwei
 * @Date 2019/10/15 0015
 * @Version V1.0
 **/
public class ExpensiveFunction implements Computable<Integer, Integer> {

    @Override
    public Integer compute(Integer arg) throws InterruptedException {
        Random random = new Random();

        TimeUnit.SECONDS.sleep(random.nextInt(5));
        return arg * 3;
    }
}
