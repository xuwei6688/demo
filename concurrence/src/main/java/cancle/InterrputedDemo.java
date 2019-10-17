package cancle;

import java.util.concurrent.TimeUnit;

/**
 *  阻塞库的方法Thread.sleep() Object.wait()等都会检查线程何时中断，并在发现中断时提前返回
 *  它们在响应中断时会做：清除中断状态，抛出InterruptedException
 *
 *  调用interrupt()并不意味着立即停止目标线程正在进行的工作，而只是传递了请求中断的消息
 **/
public class InterrputedDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(20);
                System.out.println("======================");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getId() + "被中断了");
            }
        });
        thread.start();

//        TimeUnit.SECONDS.sleep(2);
//        thread.interrupt();
//        System.out.println("中断" + thread.getId());

    }
}
