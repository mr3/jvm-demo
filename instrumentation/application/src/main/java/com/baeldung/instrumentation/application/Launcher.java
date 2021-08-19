package com.baeldung.instrumentation.application;

import java.util.Arrays;

/**
 * Created by adi on 6/14/18.
 */
public class Launcher {
    public static void main(String[] args) throws Exception {
        System.out.printf("Launcher args: %s%n", Arrays.toString(args));
        if("StartMyAtmApplication".equals(args[0])) {
            System.out.println("[Application] Starting ATM application");
            MyAtmApplication.run(args);
        } else if("LoadAgent".equals(args[0])) {
            AgentLoader.run(args);
        }
    }
}
