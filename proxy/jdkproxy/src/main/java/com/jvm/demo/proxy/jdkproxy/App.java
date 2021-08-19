package com.jvm.demo.proxy.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * App
 *
 * @author zhang_hy
 * @date 2021/03/04
 */
public class App {
    static {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }

    public static void main(String[] args) {
        HttpRequestTemplate target = new HttpRequestTemplateImpl();
        HttpRequestTemplate proxy = (HttpRequestTemplate) Proxy
            .newProxyInstance(App.class.getClassLoader(), new Class[] {HttpRequestTemplate.class},
                new HttpRequestInvocationHandler(target));

        proxy.get("mark");
        proxy.post("mark");
    }
}
