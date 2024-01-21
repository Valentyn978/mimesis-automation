package com.configuration.driver;

import com.configuration.MimesisConfig;
import com.configuration.reporting.DotTestListener;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;

import java.util.concurrent.TimeUnit;

public class WebDriverSetUp {

    private WebDriver driver;
    private MimesisConfig pr;

    public WebDriverSetUp() {
        pr = ConfigFactory.create(MimesisConfig.class);
    }

    public WebDriver getDriver(ITestContext context) {
        setUp(context);
        return driver;
    }

    private void setUp(ITestContext context) {

        if (null != context.getCurrentXmlTest().getParameter("browserType")) {
            switch (context.getCurrentXmlTest().getParameter("browserType")) {
                case "FF":
                    System.setProperty("webdriver.gecko.driver", pr.gekoDrPath());
                    driver = new FirefoxDriver();
                    break;
                case "CHROME":
                    System.setProperty("webdriver.chrome.driver", pr.chromeDriverPath());
                    driver = new ChromeDriver();
                    break;
                default:
                    driver = null;
                    DotTestListener.log("Type of browser not valid!");
            }

            driver.manage().timeouts().implicitlyWait(pr.timeWAIT(), TimeUnit.MILLISECONDS);
            driver.manage().timeouts().pageLoadTimeout(pr.timeOut(), TimeUnit.SECONDS);
            driver.manage().window().setSize(new Dimension(1024, 900));
            driver.manage().window().setPosition(new Point(60, 1));
            driver.manage().deleteAllCookies();
        }
    }
}
