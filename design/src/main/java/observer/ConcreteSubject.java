package observer;

/**
 * 具体目标类
 **/
public class ConcreteSubject extends Subject {

    public void businessMethod() {
        System.out.println("执行业务逻辑");
        notifyObserver();
    }
}

