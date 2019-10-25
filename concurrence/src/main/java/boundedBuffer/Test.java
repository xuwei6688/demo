package boundedBuffer;

import util.SleepUtil;

/**
 * @Author xuwei
 * @Date 2019/10/18 0018
 * @Version V1.0
 **/
public class Test {
    public static void main(String[] args) {
        ConditionBoundedBuffer<String> buffer = new ConditionBoundedBuffer<>(5);

        //消费者
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    System.out.println(buffer.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        //生产者
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                SleepUtil.sleep(1);
                try {
                    buffer.put("哈哈");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
