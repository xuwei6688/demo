package interview.printABC;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程交替执行ABC
 * 用同一把锁创建三个condition，并且维护一个变量 state，每个线程在执行时先对3求余数，判断是否轮到自己执行，
 * 如果没有轮到自己执行就 await ，如果轮到自己执行就输出，执行完毕后更新 state 并唤醒下一个执行的线程。
 * @Author xuwei
 * @Date 2020/5/4
 * @Version V1.0
 **/
public class PrintABC {
    private  Lock lock = new ReentrantLock();
    private  Condition conditionA = lock.newCondition();
    private  Condition conditionB = lock.newCondition();
    private  Condition conditionC = lock.newCondition();
    private int state = 0;

    public void printABC() {
        new Thread(()->{
            while (true) {
                lock.lock();
                try {
                    if (state % 3 != 0) {
                        conditionA.await();
                    } else {
                        System.out.println("A");
                        state++;
                        conditionB.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(()->{
            while (true) {
                lock.lock();
                try {
                    if (state % 3 != 1) {
                        conditionB.await();
                    } else {
                        System.out.println("B");
                        state++;
                        conditionC.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
        new Thread(()->{
            while (true) {
                lock.lock();
                try {
                    if (state % 3 != 2) {
                        conditionC.await();
                    } else {
                        System.out.println("C");
                        state++;
                        conditionA.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

    }

    public static void main(String[] args) {
        new PrintABC().printABC();
    }

}
