package deadLock;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *  Java并发编程实战 10.1.3 协作对象之间发生的死锁
 *  这个例子中虽然没有任何方法显示的获取两个锁
 *  但是看setLocation方法，它首先会获取Taxi的对象锁，然后在方法内又调用了Dispatcher的notifyAvailable方法，
 *  这个方法被Dispatcher的对象锁保护，等于说如果调用setLocation方法需要依次拿到Taxi、Dispatcher的锁。
 *  再看getPoints方法，类似，首先需要获取Dispatcher的锁，然后循环中调用了getLocation方法，所以需要获取对应taxi对象的锁
 *  这样就同样造成了对两个锁的不同顺序持有，同样容易引发死锁
 **/
public class DeadLockDemo3 {

}
class Taxi{
    private Point location,destination;
    private Dispatcher dispatcher;

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
    public synchronized Point getLocation() {
        return location;
    }
    //通过GPS更新位置信息
    public synchronized void setLocation(Point location) {
        this.location = location;
        //如果车辆到达目的是地，通知车辆调度中新车辆可用
        if (location.equals(destination)) {
            dispatcher.notifyAvailable(this);
        }
    }
}
class Dispatcher{
    private final Set<Taxi> taxis;
    private final Set<Taxi> availableTaxis;

    public Dispatcher(Set<Taxi> taxis, Set<Taxi> availableTaxis) {
        this.taxis = taxis;
        this.availableTaxis = availableTaxis;
    }

    public synchronized void notifyAvailable(Taxi taxi) {
        availableTaxis.add(taxi);
    }

    public synchronized List<Point> getPoints() {
        List<Point> points = new ArrayList<>();
        for (Taxi taxi : taxis) {
            points.add(taxi.getLocation());
        }
        return points;
    }
}
