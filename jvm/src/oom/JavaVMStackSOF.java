package oom;

/**
 *  2.4.2
 *  -Xss128k
 *  模拟StackOverflowError
 **/
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        //通过增加局部变量使栈帧变大，在相同栈容量下发生stackOverFlow时深度约小
//        int z1 = 234;
//        int z2 = ++z1;
//        int z3 = ++z2;
//        int z4 = ++z3;
//        int z5 = ++z4;
//        int z6 = ++z1;
//        int z7 = ++z2;
//        int z8 = ++z3;
//        int z9 = ++z4;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF sof = new JavaVMStackSOF();
        try {
            sof.stackLeak();
        } catch (Throwable throwable) {
            System.out.println("stack length:" + sof.stackLength);
            throw throwable;
        }
    }
}
