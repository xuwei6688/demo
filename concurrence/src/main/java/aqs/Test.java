package aqs;

import aqs.Mutex;
import util.SleepUtil;

/**
 * @Author xuwei
 * @Date 2019/10/23 0023
 * @Version V1.0
 **/
public class Test {
    public static void main(String[] args) {
        Mutex mutex = new Mutex();

        //线程1持有锁，并占用5秒
        new Thread(()->{
            mutex.lock();
            try{
                SleepUtil.sleep(5);
                System.out.println("=============");
            }finally {
                mutex.unlock();
            }
        }).start();

        //线程2持有锁
        new Thread(()->{
            mutex.lock();
            try{
                System.out.println("**************");
            }finally {
                mutex.unlock();
            }
        }).start();
    }
}
