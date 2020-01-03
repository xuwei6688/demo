package allocation;

/**
 * @Author xuwei
 * @Date 2019/11/5 0005
 * @Version V1.0
 **/
public class StaticDispatch {
    static abstract class Human{

    }
    static class Man extends Human{}
    static class Woman extends Human{}

    public void sayHello(Human guy) {
        System.out.println("hello human");
    }
    public void sayHello(Man guy) {
        System.out.println("hello man");
    }
    public void sayHello(Woman guy) {
        System.out.println("hello woman");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch dispatch = new StaticDispatch();
        dispatch.sayHello(man);
        dispatch.sayHello(woman);
    }

}
