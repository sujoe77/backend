package com.baeldung.instrumentation.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

public class MyInstrumentationAgent {
    public static final String CLASS_NAME = "com.baeldung.instrumentation.application.MyAtm";
    private static Logger LOGGER = LoggerFactory.getLogger(MyInstrumentationAgent.class);

    public static void premain(String agentArgs, Instrumentation inst) {
        LOGGER.info("[Agent] In premain method");
        transformClass(CLASS_NAME, inst);
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        LOGGER.info("[Agent] In agentmain method");
        transformClass(CLASS_NAME, inst);
    }

    private static void transformClass(String className, Instrumentation instrumentation) {
        Class<?> targetCls = getClass(className, instrumentation);
        transform(
                getClass(className, instrumentation),
                instrumentation,
                new AtmTransformer(targetCls.getName(), targetCls.getClassLoader())
        );
    }

    private static Class<?> getClass(String className, Instrumentation instrumentation) {
        Class<?> targetCls = null;
        // see if we can get the class using forName
        try {
            targetCls = Class.forName(className);
        } catch (Exception ex) {
            LOGGER.error("Class [{}] not found with Class.forName");
        }
        // otherwise iterate all loaded classes and find what we want
        for (Class<?> clazz : instrumentation.getAllLoadedClasses()) {
            if (clazz.getName().equals(className)) {
                return clazz;
            }
        }
        if (targetCls != null) {
            return targetCls;
        }
        throw new RuntimeException("Failed to find class [" + className + "]");
    }

    private static void transform(Class<?> clazz, Instrumentation instrumentation, AtmTransformer dt) {
        instrumentation.addTransformer(dt, true);
        try {
            instrumentation.retransformClasses(clazz);
        } catch (Exception ex) {
            throw new RuntimeException("Transform failed for class: [" + clazz.getName() + "]", ex);
        }
    }
}
