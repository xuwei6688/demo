package communication;

/**
 * 调用 threadA.join(); 当前线程会等待 threadA 执行完毕才会继续执行。
 * 如果调用 threadA.join(); 时，thread还没有start，那么当前线程就不会等待。
 * @Author xuwei
 * @Date 2019/12/24 0024
 * @Version V1.0
 **/
public class JoinDemo {
    private class A extends Thread{
        @Override
        public void run() {
            System.out.println("A");
        }
    }
    private class B extends Thread{
        private A a;
        public B(A a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                a.join();
                System.out.println("B");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void test() {
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();
    }

    public static void main(String[] args) {
        JoinDemo joinDemo = new JoinDemo();
        joinDemo.test();
    }
}
