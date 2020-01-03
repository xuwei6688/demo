package oom;

import java.util.ArrayList;
import java.util.List;

/**
 *  深入理解JVM 2.4.1
 *  模拟堆内存溢出
 *  -Xmx20m -Xms20m -XX:+HeapDumpOnOutOfMemoryError
 *  对dump文件分析，确认是发生了内存泄漏还是内存溢出
 **/
public class HeapOOM {
    static class OOMObject{

    }

    public static void main(String[] args) {
       List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.remove(0);
    }
}
