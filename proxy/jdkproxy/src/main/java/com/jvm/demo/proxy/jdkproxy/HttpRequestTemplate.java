package com.jvm.demo.proxy.jdkproxy;

/**
 * HttpRequestTemplate
 *
 * @author zhang_hy
 * @date 2021/03/04
 */
public interface HttpRequestTemplate {
    String get(String request);
    String post(String request);
}
