package com.baeldung.instrumentation.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by adi on 6/11/18.
 */
public class MyAtm {
    // private static Logger LOGGER = LoggerFactory.getLogger(MyAtm.class);

    public static void withdrawMoney(long amount) throws InterruptedException {
        Thread.sleep(amount); //processing going on here
        System.out.println("------------------------------------------");
        System.out.printf("[Application] Successful Withdrawal of [%d] units!%n", amount);

    }
}
