package communication;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition的await和signal/signalAll分别对应Object的wait和notify/notifyAll
 * 区别是condition可以通过创建多个condition对等待分组，调用condition,notifyAll()只唤醒同一组的
 */
public class ConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("threadA");
        threadA.start();

        ThreadB threadB = new ThreadB(service);
        threadB.setName("threadB");
        threadB.start();

        TimeUnit.SECONDS.sleep(1);
        service.signalAll_A();
    }

}
class ThreadA extends Thread{
    private Service service;

    ThreadA(Service service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.awaitA();
    }
}
class ThreadB extends Thread{
    private Service service;

    ThreadB(Service service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.awaitB();
    }
}

class Service{
    private Lock  lock = new ReentrantLock();

    //使用多个Condition实现通知部分线程
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();

    public void awaitA(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始等待");
            conditionA.await();
            System.out.println(Thread.currentThread().getName() + "结束等待");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void awaitB(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始等待");
            conditionB.await();
            System.out.println(Thread.currentThread().getName() + "结束等待");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void signalAll_A() {
        lock.lock();
        try{
            conditionA.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void signalAll_B() {
        lock.lock();
        try{
            conditionB.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
