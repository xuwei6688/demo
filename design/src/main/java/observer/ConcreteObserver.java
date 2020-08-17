package observer;

/**
 * @Author xuwei
 * @Date 2020/8/16
 * @Version V1.0
 **/
public class ConcreteObserver implements Observer{

    @Override
    public void update() {
        System.out.println("观察到业务完成");
    }
}
