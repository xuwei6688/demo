import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author xuwei
 * @Date 2019/12/3 0003
 * @Version V1.0
 **/
public class Test {
    static Lock lock = new ReentrantLock();
    static Condition conditionA = lock.newCondition();
    static Condition conditionB = lock.newCondition();
    static Condition conditionC = lock.newCondition();
    public static void print() {

        new Thread(()->{
            while (true) {
                lock.lock();
                try{
                    conditionA.await();
                    System.out.print("A");
                    conditionB.signal();
                }catch (InterruptedException e){
                    e.printStackTrace();
                } finally{
                    lock.unlock();
                }
            }
        }).start();

        new Thread(()->{
            while (true) {
                lock.lock();
                try{
                    conditionB.await();
                    System.out.print("B");
                    conditionC.signal();
                }catch (InterruptedException e){
                    e.printStackTrace();
                } finally{
                    lock.unlock();
                }
            }
        }).start();

        new Thread(()->{
            while (true) {
                lock.lock();
                try{
                    conditionC.await();
                    System.out.print("C");
                    conditionA.signal();
                }catch (InterruptedException e){
                    e.printStackTrace();
                } finally{
                    lock.unlock();
                }
            }
        }).start();
    }

    public static void main(String[] args) {

    }
}
