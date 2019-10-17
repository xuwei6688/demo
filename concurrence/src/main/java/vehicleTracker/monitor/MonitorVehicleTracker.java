package vehicleTracker.monitor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 车辆追踪器
 * 基于java监视器模式保证线程安全性
 * 虽然MutablePoint不是线程安全的，但是locations,和MutablePoint都没有发布
 * 在需要返回车辆位置时，通过拷贝方法返回一个新的MutablePoint，这样
 * @Author xuwei
 * @Date 2019/10/14 0014
 * @Version V1.0
 **/
public class MonitorVehicleTracker {
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
        if (loc == null) {
            throw new IllegalArgumentException("No such ID:" + id);
        }
        loc.x = x;
        loc.y = y;
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> locations) {

        Map<String, MutablePoint> result = new HashMap<>();
        for (String id : locations.keySet()) {
            result.put(id, locations.get(id));
        }

        return Collections.unmodifiableMap(result);
    }
}
