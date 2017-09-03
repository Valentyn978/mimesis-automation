package com.configuration.reporting;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class DotTestListener extends TestListenerAdapter {
    private static int m_count = 0;

    public static void log(String string) {
        System.out.print(string + "\r\n");
        Reporter.log(string + "<br />");
        if (++m_count % 40 == 0) {
            System.out.println("");
        }
    }

    public static void logWar(String string) {
        System.out.print(string + "\r\n");
        Reporter.log("<p>" + string + "</p>");
        if (++m_count % 40 == 0) {
            System.out.println("");
        }
    }

    public static void logOnly(String string) {
        System.out.print(string + "\r\n");
        if (++m_count % 40 == 0) {
            System.out.println("");
        }
    }

    public static void logNoNewline(String string) {
        System.out.print(string);
    }

    // Reporting to Console
    @Override
    public void onStart(ITestContext tr) {
        log("Start");
    }

    @Override
    public void onFinish(ITestContext tr) {
        log("Finish");
    }

    @Override
    public void onTestStart(ITestResult tr) {
        log("(TRun)");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
        log("(FailP)");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        log("F");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log("S");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log(".");
    }

}
