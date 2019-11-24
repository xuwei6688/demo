package com.demo;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author xuwei
 * @Date 2019-10-17
 * @Version V1.0
 **/
public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz);//设置目标类
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //o是目标类  method是目标类反射方法  args 方法入参  methodProxy 代理类

        System.out.println("before print");
        //调用目标类方法
        Object invoke = methodProxy.invokeSuper(o, args);
        System.out.println("after print");
        return invoke;
    }
}
