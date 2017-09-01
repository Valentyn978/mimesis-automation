package com.configuration.reporting;

import com.helpers.DataProcessing;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.testng.Reporter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class TestLogger extends Logger {

    public static Queue<String> logMessages = new ConcurrentLinkedQueue<>();
    private static final TestLoggerFactory BASE_LOGGER_FACTORY = new TestLoggerFactory();
    private DataProcessing dataProcessing = new DataProcessing();
    static String FQCN = TestLogger.class.getName();

    protected TestLogger(String name) {
        super(name);
        logMessages.clear();
    }

    public static Logger getLogger(Class aClass) {
        return LogManager.getLogger(aClass.getName(), BASE_LOGGER_FACTORY);
    }

    private void sendMessage(String message) {
        logMessages.add(message);
    }

    @Override
    protected void forcedLog(String fqcn, Priority level, Object message, Throwable t) {
        super.forcedLog(FQCN, level, message, t);
        sendMessage(message.toString());
    }

    @Override
    public void log(String callerFQCN, Priority level, Object message, Throwable t) {
        super.log(FQCN, level, message, t);
        sendMessage(message.toString());
    }

    @Override
    public void info(Object message) {
        if (message.toString().contains("Hibernate")) {
            return;
        }
        super.info(message);
        sendMessage(message.toString());
        Reporter.log(dataProcessing.formatMessage(message));
    }

    @Override
    public void debug(Object message) {
        super.debug(message);
        sendMessage(message.toString());
        Reporter.log(dataProcessing.formatMessage(message));
    }

    @Override
    public void warn(Object message) {
        super.warn(message);
        sendMessage(message.toString());
        Reporter.log(dataProcessing.formatMessage("<p>" + message + "</p>"));
    }

    @Override
    public void error(Object message) {
        super.error(message);
        sendMessage(message.toString());
        Reporter.log(dataProcessing.formatMessage("<p>" + message + "</p>"));
    }

    @Override
    public void trace(Object message) {
        super.trace(message);
        sendMessage(message.toString());
        Reporter.log(dataProcessing.formatMessage(message));
    }
}
