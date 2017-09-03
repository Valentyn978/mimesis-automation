package com.configuration.reporting;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.helpers.PropertiesLoader;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.*;
import org.testng.internal.ResultMap;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.uncommons.reportng.HTMLReporter;
import java.io.*;
import java.util.*;

public class TestHtmlReporter extends HTMLReporter {

    private static Logger logger = Logger.getLogger(TestHtmlReporter.class);
    private static PropertiesLoader properties = new PropertiesLoader();

    public static final String FOLDER_NAME_REPORT = "html_report";
    public static final String PATH_TO_SCREEN_SHOTS = "path-to-images";
    private static boolean ALREADY_GENERATED = false;


    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectoryName) {

        if (!ALREADY_GENERATED){
            ALREADY_GENERATED = true;
        } else {
            return;
        }
        String dir = System.getProperty(TestListener.CURRENT_TEST_REPORT_DIR) + File.separator + FOLDER_NAME_REPORT;

        File finalDir = new File(dir);
        if (finalDir.exists()) {
            try {
                FileUtils.deleteDirectory(finalDir);
            } catch (IOException e) {
                logger.error(e);
            }
        }
        finalDir.mkdirs();
        List<ISuite> inputSuites = getSuitesWithGrandTotal(suites);
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        super.generateReport(xmlSuites, inputSuites, dir);

        File tmpDir = new File(dir + "/html");
        try {
            FileUtils.copyDirectory(tmpDir, finalDir);
            FileUtils.deleteDirectory(tmpDir);

            //copy screen shots
            String property = System.getProperty(PATH_TO_SCREEN_SHOTS);
            if (property != null) {
                File dirWithScreenShots = new File(property);
                if (dirWithScreenShots.exists()) {
                    FileUtils.moveDirectoryToDirectory(dirWithScreenShots, finalDir, true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("HTML report folder is " + finalDir.getPath());
    }

    private List<ISuite> getSuitesWithGrandTotal(List<ISuite> inputSuites) {
        List<ISuite> suites = new ArrayList<>(inputSuites);
        // find Idx of empty Suite
        Integer totalSuiteIdx = null;
        for (ISuite suite : suites) {
            if (suite.getResults().size() == 0) {
                totalSuiteIdx = suites.indexOf(suite);
            }
        }
        // fill total Suite
        if (totalSuiteIdx != null) {
            ISuite totalSuite = suites.get(totalSuiteIdx);
            suites.remove((int) totalSuiteIdx);
            // copy all test results to total suite
            Map<String, ISuiteResult> totalSuiteResults = totalSuite.getResults();
            ISuiteResult suiteResultForReport = null;
            for (ISuite suite : suites) {
                for (String key : suite.getResults().keySet()) {
                    ITestContext source = suite.getResults().get(key).getTestContext();
                    if (!totalSuiteResults.containsKey(key)) {
                        suiteResultForReport = getSuiteResult(totalSuite, source);
                        totalSuiteResults.put(key, suiteResultForReport);
                    }
                    copyTestContext(source, totalSuiteResults.get(key).getTestContext());
                }
            }
            suites.add(totalSuite);
        }
        return suites;
    }

    private ISuiteResult getSuiteResult(final ISuite suite, final ITestContext context) {
        return new ISuiteResult() {
            ITestContext iTestContext = TestHtmlReporter.this.getTestContext(suite, context);

            @Override
            public String getPropertyFileName() {
                return suite.getXmlSuite().getFileName();
            }

            @Override
            public ITestContext getTestContext() {
                return iTestContext;
            }
        };
    }

    private ITestContext getTestContext(final ISuite suite, final ITestContext context) {
        return new ITestContext() {
            private IResultMap m_passedTests = new ResultMap();
            private IResultMap m_failedTests = new ResultMap();
            private IResultMap m_failedButWithinSuccessPercentageTests = new ResultMap();
            private IResultMap m_skippedTests = new ResultMap();

            @Override
            public String getName() {
                return context.getName();
            }

            @Override
            public Date getStartDate() {
                return context.getStartDate();
            }

            @Override
            public Date getEndDate() {
                return context.getEndDate();
            }

            @Override
            public IResultMap getPassedTests() {
                return m_passedTests;
            }

            @Override
            public IResultMap getSkippedTests() {
                return m_skippedTests;
            }

            @Override
            public IResultMap getFailedButWithinSuccessPercentageTests() {
                return m_failedButWithinSuccessPercentageTests;
            }

            @Override
            public IResultMap getFailedTests() {
                return m_failedTests;
            }

            @Override
            public String[] getIncludedGroups() {
                return new String[0];
            }

            @Override
            public String[] getExcludedGroups() {
                return new String[0];
            }

            @Override
            public String getOutputDirectory() {
                return suite.getOutputDirectory();
            }

            @Override
            public ISuite getSuite() {
                return suite;
            }

            @Override
            public ITestNGMethod[] getAllTestMethods() {
                return suite.getAllMethods().toArray(new ITestNGMethod[]{});
            }

            @Override
            public String getHost() {
                return suite.getHost();
            }

            @Override
            public Collection<ITestNGMethod> getExcludedMethods() {
                return suite.getExcludedMethods();
            }

            @Override
            public IResultMap getPassedConfigurations() {
                return new ResultMap();
            }

            @Override
            public IResultMap getSkippedConfigurations() {
                return new ResultMap();
            }

            @Override
            public IResultMap getFailedConfigurations() {
                return new ResultMap();
            }

            @Override
            public XmlTest getCurrentXmlTest() {
                return null;
            }

            @Override
            public List<Module> getGuiceModules(Class<? extends Module> aClass) {
                return null;
            }

            @Override
            public void addGuiceModule(Class<? extends Module> aClass, Module module) {
            }

            @Override
            public Injector getInjector(List<Module> modules) {
                return null;
            }

            @Override
            public void addInjector(List<Module> modules, Injector injector) {
            }

            @Override
            public Object getAttribute(String s) {
                return suite.getAttribute(s);
            }

            @Override
            public void setAttribute(String s, Object o) {
                suite.setAttribute(s, o);
            }

            @Override
            public Set<String> getAttributeNames() {
                return suite.getAttributeNames();
            }

            @Override
            public Object removeAttribute(String s) {
                return suite.removeAttribute(s);
            }
        };
    }

    private void copyTestContext(ITestContext source, ITestContext target) {
        copyResultMap(source.getPassedTests(), target.getPassedTests());
        copyResultMap(source.getFailedTests(), target.getFailedTests());
        copyResultMap(source.getSkippedTests(), target.getSkippedTests());
        copyResultMap(source.getFailedButWithinSuccessPercentageTests(), target.getFailedButWithinSuccessPercentageTests());
    }

    private void copyResultMap(IResultMap source, IResultMap target) {
        for (ITestNGMethod method : source.getAllMethods()) {
            for (ITestResult result : source.getResults(method)) {
                target.addResult(result, method);
            }
        }
    }
}