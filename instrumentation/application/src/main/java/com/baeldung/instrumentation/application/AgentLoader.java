package com.baeldung.instrumentation.application;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Optional;

/**
 * Created by adi on 6/10/18.
 */
public class AgentLoader {
    // private static Logger LOGGER = LoggerFactory.getLogger(AgentLoader.class);

    public static void run(String[] args) {
        String agentFilePath = "..\\agent\\target\\agent-1.0.jar";
        String applicationName = "MyAtmApplication";

        //iterate all jvms and get the first one that matches our application name
        System.out.println("JVM running list on local machine:");
        Optional<VirtualMachineDescriptor> jvmProcessOpt = VirtualMachine.list()
          .stream()
          .filter(jvm -> {
              System.out.println(jvm.toString());
              return jvm.displayName().contains(applicationName);
          })
          .findFirst();

        if(!jvmProcessOpt.isPresent()) {
            System.out.println("Target Application not found");
            return;
        }
        File agentFile = new File(agentFilePath);
        try {
            String jvmPid = jvmProcessOpt.get().id();
            System.out.println("Attaching to target JVM with PID: " + jvmPid);
            VirtualMachine jvm = VirtualMachine.attach(jvmPid);
            jvm.loadAgent(agentFile.getAbsolutePath());
            jvm.detach();
            System.out.println("Attached to target JVM and loaded Java agent successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
