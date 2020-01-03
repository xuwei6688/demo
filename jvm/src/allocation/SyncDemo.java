package allocation;

/**
 * @Author xuwei
 * @Date 2019/11/6 0006
 * @Version V1.0
 **/
public final class SyncDemo {
    private volatile int number;

    public  void incr() {
        number++;
    }

    public void incr2() {
        int num = number;
        num = num + 1;
        number = num;
    }

}
