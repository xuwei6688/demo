package interview.print99;

/**
 * 两个线程交替打印0-99
 **/
public class Test1 {
    private int number = 0;
    private final Object lock = new Object();

    class PrintNumber implements Runnable{

        @Override
        public void run() {
            while (number <= 99) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + ":" + number++);
                    lock.notifyAll();
                    if (number <= 99) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void circlePrint() {
        new Thread(new PrintNumber(), "Thread1").start();
        new Thread(new PrintNumber(), "Thread2").start();
    }

    public static void main(String[] args) {
        new Test1().circlePrint();
    }
}



