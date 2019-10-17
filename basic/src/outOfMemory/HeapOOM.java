package outOfMemory;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=E:\
 *
 * -XX:+HeapDumpOnOutOfMemoryError  输出堆OOM信息
 * -XX:HeapDumpPath=E:\             输出位置
 */
public class HeapOOM {
    static class OOMObj{

    }

    public static void main(String[] args) {
        List<OOMObj> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObj());
        }
    }
}
