package observer;


public class Test {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.attach(new ConcreteObserver());
        subject.businessMethod();
    }
}
