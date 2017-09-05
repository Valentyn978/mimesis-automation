package com.configuration.driver;

import com.helpers.PropertiesLoader;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.TestNGException;
import java.io.File;
import java.util.concurrent.TimeUnit;


public class WebDriverSetUp {

    private WebDriver driver;
    private PropertiesLoader pr = new PropertiesLoader();

    public WebDriver getDriver(ITestContext context) {
        setUp(context);
        return driver;
    }

    private void setUp(ITestContext context) {

        String parameterOfSuite = "browserType";
        switch (context.getCurrentXmlTest().getParameter(parameterOfSuite)) {
            case "FF":
                String gekoDrPath = pr.getProperty("GekoDrPath");
                if (!new File(gekoDrPath).exists())
                    throw new TestNGException("Driver for FireFox browser does not present, see 'base.properties' file");
                System.setProperty("webdriver.gecko.driver", gekoDrPath);
                driver = new FirefoxDriver();
                break;
            case "CHROME":
                String chromeDriverPath = pr.getProperty("ChromeDriverPath");
                if (!new File(chromeDriverPath).exists())
                    throw new TestNGException("Driver for Chrome browser does not present, see 'base.properties' file");
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                driver = new ChromeDriver();
                break;
            default:
                driver = null;
                throw new TestNGException(String.format("Type of browser not valid, " +
                        "see parameter %s in 'Suite.xml' file.", parameterOfSuite));
        }

        driver.manage().timeouts().implicitlyWait(Integer.parseInt(pr.getProperty("TimeWAIT")), TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(pr.getProperty("TimeOut")), TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1024, 900));
        driver.manage().window().setPosition(new Point(60, 1));
        driver.manage().deleteAllCookies();
    }
}
