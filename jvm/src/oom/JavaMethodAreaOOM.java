package oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 模拟方法区溢出
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 **/
public class JavaMethodAreaOOM {
    public static void main(String[] args) {
//        while (true) {
//            Enhancer enhancer = new Enhancer();
//            enhancer.setSuperclass(HeapOOM.class);
//            enhancer.setUseCache(false);
//            enhancer.setCallback(new MethodInterceptor() {
//                @Override
//                public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//                    return methodProxy.invokeSuper(o, args);
//                }
//            });
//            enhancer.create();
//        }
            List<String> list = new ArrayList<String>();
            int i = 0;
            while (true) {
                list.add(String.valueOf(i++).intern());
            }
    }
}
