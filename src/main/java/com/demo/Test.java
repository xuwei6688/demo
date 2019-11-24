package com.demo;

import java.lang.reflect.Proxy;

/**
 * @Author xuwei
 * @Date 2019-10-17
 * @Version V1.0
 **/
public class Test {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        PrintService proxy = (PrintService) cglibProxy.getProxy(PrintService.class);
        proxy.printHelloWorld();

    }
}
