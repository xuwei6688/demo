package vehicleTracker.delegate;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 车辆追踪器
 * 基于委托实现，构成车辆位置的locations是线程安全的，所以
 * @Author xuwei
 * @Date 2019/10/14 0014
 * @Version V1.0
 **/
public class DelegatingVehicleTracker {
    /**
     *  key:车辆id  value:车辆位置
     */
    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        this.locations = new ConcurrentHashMap<>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null) {
            throw new IllegalArgumentException("车辆不存在！");
        }
    }
}
