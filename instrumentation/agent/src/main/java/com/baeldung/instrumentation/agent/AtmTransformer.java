package com.baeldung.instrumentation.agent;

import javassist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class AtmTransformer implements ClassFileTransformer {

    // private static Logger LOGGER = LoggerFactory.getLogger(AtmTransformer.class);

    private static final String WITHDRAW_MONEY_METHOD = "withdrawMoney";

    /** The internal form class name of the class to transform */
    private String targetClassName;
    /** The class loader of the class we want to transform */
    private ClassLoader targetClassLoader;

    public AtmTransformer(String targetClassName, ClassLoader targetClassLoader) {
        this.targetClassName = targetClassName;
        this.targetClassLoader = targetClassLoader;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] byteCode = classfileBuffer;

        String finalTargetClassName = this.targetClassName.replaceAll("\\.", "/"); //replace . with /
        if (!className.equals(finalTargetClassName)) {
            return byteCode;
        }

        CtClass.debugDump = "./dump";

        if (loader.equals(targetClassLoader)) {
            // LOGGER.info("[Agent] Transforming class MyAtm");
            System.out.println("[Agent] Transforming class MyAtm");
            try {
                ClassPool cp = ClassPool.getDefault();
                CtClass cc = cp.get(targetClassName);
                CtMethod m = cc.getDeclaredMethod(WITHDRAW_MONEY_METHOD);
                m.addLocalVariable("startTime", CtClass.longType);
                m.insertBefore("startTime = System.currentTimeMillis();");

                StringBuilder endBlock = new StringBuilder();

                m.addLocalVariable("endTime", CtClass.longType);
                m.addLocalVariable("opTime", CtClass.longType);
                endBlock.append("endTime = System.currentTimeMillis();");
                endBlock.append("opTime = endTime-startTime;");

                endBlock.append("System.out.println(\"[Application Transformer] Withdrawal operation completed in:\" + opTime + \" milliseconds!\");");

                m.insertAfter(endBlock.toString());

                byteCode = cc.toBytecode();
                cc.detach();
            } catch (NotFoundException | CannotCompileException | IOException e) {
                // LOGGER.error("Exception", e);
                System.out.println(e);
            }
        }
        return byteCode;
    }
}
