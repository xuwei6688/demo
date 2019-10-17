package memorizer;

/**
 * Java并发编程实战 5.6
 * 一个计算接口
 * @Author xuwei
 * @Date 2019/10/15 0015
 * @Version V1.0
 **/
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
