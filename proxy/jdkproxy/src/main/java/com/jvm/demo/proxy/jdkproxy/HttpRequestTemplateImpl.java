package com.jvm.demo.proxy.jdkproxy;

/**
 * HttpReqeustTemplateImpl
 *
 * @author zhang_hy
 * @date 2021/03/04
 */
public class HttpRequestTemplateImpl implements HttpRequestTemplate {
    @Override
    public String get(String request) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "get";
    }

    @Override
    public String post(String request) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "post";
    }
}
