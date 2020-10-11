package com.baeldung.instrumentation.application;

import com.sun.tools.attach.VirtualMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Optional;

/**
 * Created by adi on 6/10/18.
 */
public class AgentLoader {
    public static final String AGENT_FILE_PATH = "/home/adi/Desktop/agent-1.0.0-jar-with-dependencies.jar";

    private static Logger LOGGER = LoggerFactory.getLogger(AgentLoader.class);

    public static void run(String[] args) {
        Optional<String> jvmPid;
        if ((jvmPid = findPid()) != null) {
            loadAgent(jvmPid.get(), AGENT_FILE_PATH);
        }
    }

    private static Optional<String> findPid() {
        String applicationName = MyAtmApplication.class.getSimpleName();

        //iterate all jvms and get the first one that matches our application name
        Optional<String> jvmPid = Optional.ofNullable(VirtualMachine.list()
                .stream()
                .filter(jvm -> {
                    LOGGER.info("jvm:{}", jvm.displayName());
                    return jvm.displayName().contains(applicationName);
                })
                .findFirst().get().id());

        if (!jvmPid.isPresent()) {
            LOGGER.error("Target Application not found");
            return null;
        }
        return jvmPid;
    }

    private static void loadAgent(String jvmPid, String agentFilePath) {
        try {
            LOGGER.info("Attaching to target JVM with PID: " + jvmPid);
            VirtualMachine jvm = VirtualMachine.attach(jvmPid);
            jvm.loadAgent(new File(agentFilePath).getAbsolutePath());
            jvm.detach();
            LOGGER.info("Attached to target JVM and loaded Java agent successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
