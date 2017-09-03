package com.helpers;

import org.apache.log4j.Logger;

public class WaitUtils {

    private static final Logger logger = Logger.getLogger(WaitUtils.class);

    public static void waitForOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.warn("One second timeout was not executed. Error: " + e.getMessage());
        }
    }

    public static void waitForMilliseconds() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            logger.warn("100ms timeout was not executed. Error: " + e.getMessage());
        }
    }
}
