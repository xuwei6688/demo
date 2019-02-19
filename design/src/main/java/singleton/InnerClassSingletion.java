package singleton;

/**
 * 原理：类在加载到jvm里时静态内部类不会初始化，只有第一次调用getInstance()时被加载到jvm中，并且jvm保证静态内部类只被加载一次。
 */
public class InnerClassSingletion {

    private InnerClassSingletion(){}

    private static class InnerClass{
        private static InnerClassSingletion instance = new InnerClassSingletion();
    }

    public InnerClassSingletion getInstance() {
        return InnerClass.instance;   
    }
}
