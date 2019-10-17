package deadLock;


import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  java并发编程实战 10.1.4 开放调用
 *  想demo3中调用方法持有锁A，而被调方法持有锁B，引发死锁。
 *  解决的方法是我们调用其他类的方法时不需要持有锁----也称作开放调用
 *  把原本作用于方法上的对象锁作用于代码块，这样调用方法时就不需要持有锁了，从而避免了死锁
 **/
public class DeadLockDemo4 {
}
class Taxi2 {
    private Point location,destination;
    private Dispatcher2 dispatcher;

    public Taxi2(Dispatcher2 dispatcher) {
        this.dispatcher = dispatcher;
    }
    public synchronized Point getLocation() {
        return location;
    }
    //通过GPS更新位置信息
    public void setLocation(Point location) {
        boolean reachedDestination = false;
        synchronized (this) {
            this.location = location;
            reachedDestination = location.equals(destination);
        }
        //如果车辆到达目的是地，通知车辆调度中新车辆可用
        if (reachedDestination) {
            dispatcher.notifyAvailable(this);
        }
    }
}
class Dispatcher2{
    private final Set<Taxi2> Taxi2s;
    private final Set<Taxi2> availableTaxi2s;

    public Dispatcher2(Set<Taxi2> Taxi2s, Set<Taxi2> availableTaxi2s) {
        this.Taxi2s = Taxi2s;
        this.availableTaxi2s = availableTaxi2s;
    }

    public synchronized void notifyAvailable(Taxi2 Taxi2) {
        availableTaxi2s.add(Taxi2);
    }

    public java.util.List<Point> getPoints() {
        Set<Taxi2> copy;
        synchronized (this) {
            copy = new HashSet<>(Taxi2s);
        }
        List<Point> points = new ArrayList<>();
        for (Taxi2 Taxi2 : copy) {
            points.add(Taxi2.getLocation());
        }
        return points;
    }
}