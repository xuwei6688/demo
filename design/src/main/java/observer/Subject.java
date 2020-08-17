package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 目标 - 被观察的对象
 **/
public abstract class Subject {
    protected List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public  void notifyObserver(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
