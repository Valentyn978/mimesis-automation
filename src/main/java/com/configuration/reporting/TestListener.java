package com.configuration.reporting;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {

    public static final String CURRENT_TEST_REPORT_DIR = "currentTestReportDir";
    private final Logger logger = TestLogger.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
            if (logger.isInfoEnabled()) {
                logger.info("##################### NEXT TEST #####################");
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Starting test: " + iTestResult.getName());
            }
            if (logger.isTraceEnabled()) {
                logger.info("Test started at: " + new DateTime(iTestResult.getStartMillis()).toString() + "<br/>");
            }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        if (logger.isInfoEnabled()) {
            logger.info("<center>################## Test result: SUCCESS ##################</center><br/>");
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.error("<center>################## Test result: FAILED ##################</center><br/>");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.warn("<center>################## Test result: SKIPPED ##################</center><br/>");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        if (logger.isTraceEnabled()) {
            logger.info("Test finished.");
            logger.info("Suit name: '" + iTestContext.getSuite().getName() + "'.");
            logger.info("Last test name: '" + iTestContext.getName() + "'.");
        }
    }
}
