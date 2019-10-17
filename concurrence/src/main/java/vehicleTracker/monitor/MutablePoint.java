package vehicleTracker.monitor;

/**
 * @Author xuwei
 * @Date 2019/10/14 0014
 * @Version V1.0
 **/
public class MutablePoint {
    public int x,y;

    public MutablePoint() {
        this.x = 0;
        this.y = 0;
    }

    public MutablePoint(MutablePoint point) {
        this.x = point.x;
        this.y = point.y;
    }
}
