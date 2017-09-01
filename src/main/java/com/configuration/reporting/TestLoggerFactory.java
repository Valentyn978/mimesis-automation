package com.configuration.reporting;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class TestLoggerFactory implements LoggerFactory {

    @Override
    public Logger makeNewLoggerInstance(String s) {
        return new TestLogger(s);
    }
}
