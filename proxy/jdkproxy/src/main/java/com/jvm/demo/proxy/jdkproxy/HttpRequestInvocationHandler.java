package com.jvm.demo.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * HttpRequestInvocationHandler
 *
 * @author zhang_hy
 * @date 2021/03/04
 */
public class HttpRequestInvocationHandler implements InvocationHandler {
    HttpRequestTemplate target;

    public HttpRequestInvocationHandler(HttpRequestTemplate template) {
        this.target = template;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return method.invoke(target, args);
        } finally {
            long end = System.currentTimeMillis();

            System.out.printf("method: %s, elapsed time: %s%n", method.getName(), end - start);
        }
    }
}
