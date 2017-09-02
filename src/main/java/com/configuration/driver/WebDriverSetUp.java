package com.configuration.driver;

import com.configuration.reporting.DotTestListener;
import com.helpers.PropertiesLoader;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;

import java.util.concurrent.TimeUnit;

public class WebDriverSetUp {

    private static WebDriver driver;
    private PropertiesLoader pr = new PropertiesLoader();

    public WebDriver getDriver(ITestContext context) {
        setUp(context);
        return driver;
    }

    private void setUp(ITestContext context) {

        switch (context.getCurrentXmlTest().getParameter("browserType")) {
            case "FF":
                System.setProperty("webdriver.gecko.driver", pr.getProperty("GekoDrPath"));
                driver = new FirefoxDriver();
                break;
            case "CHROME":
                System.setProperty("webdriver.chrome.driver", pr.getProperty("ChromeDriverPath"));
                driver = new ChromeDriver();
                break;
            default:
                driver = null;
                DotTestListener.log("Type of browser not valid!");
        }

        driver.manage().timeouts().implicitlyWait(Integer.parseInt(pr.getProperty("TimeWAIT")), TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(pr.getProperty("TimeOut")), TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(800, 600));
        driver.manage().window().setPosition(new Point(60, 1));
        driver.manage().deleteAllCookies();
    }

    public void quiteDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
