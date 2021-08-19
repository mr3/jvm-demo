package com.baeldung.instrumentation.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAtmApplication {

    // private static Logger LOGGER = LoggerFactory.getLogger(MyAtmApplication.class);

    public static void run(String[] args) throws Exception {
        MyAtm.withdrawMoney(Integer.parseInt(args[1]));
        MyAtm.withdrawMoney(Integer.parseInt(args[2]));
    }

}
