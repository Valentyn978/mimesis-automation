package com.configuration.driver;

import com.configuration.MimesisConfig;
import com.configuration.reporting.DotTestListener;
import com.configuration.reporting.TestHtmlReporter;
import com.configuration.reporting.TestListener;
import com.helpers.WaitUtils;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang.NotImplementedException;
import org.joda.time.DateTime;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class PageDriverImpl implements PageDriver {

    private static final File defaultScreenShotsFolder;

    static {
        MimesisConfig PL = ConfigFactory.create(MimesisConfig.class);
        defaultScreenShotsFolder = new File(PL.testResultFolder() + File.separator + "images");
        System.setProperty(TestHtmlReporter.PATH_TO_SCREEN_SHOTS, defaultScreenShotsFolder.getPath());
        System.setProperty(TestListener.CURRENT_TEST_REPORT_DIR, PL.testResultFolder());
    }

    private final WebDriver webDriver;

    public PageDriverImpl(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Override
    public void get(String s) {
        webDriver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return webDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webDriver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return webDriver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return webDriver.getPageSource();
    }

    @Override
    public void close() {
        webDriver.close();
    }

    @Override
    public void quit() {
        webDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return webDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return webDriver.getWindowHandle();
    }

    @Override
    public WebDriver.TargetLocator switchTo() {
        return webDriver.switchTo();
    }

    @Override
    public WebDriver.Navigation navigate() {
        return webDriver.navigate();
    }

    @Override
    public WebDriver.Options manage() {
        return webDriver.manage();
    }

    public void clickElementByIndex(String xPath, int n) {
        List<WebElement> elements = findElements(By.xpath(xPath));
        WebElement requiredElement = elements.get(n);

        requiredElement.click();
        WaitUtils.waitForOneSecond();
        DotTestListener.logOnly("WebElement is clicked. With xPath: " + xPath);
        DotTestListener.logOnly("TagName: " + requiredElement.getTagName());
    }

    public void clickObjectByXPath(String xPath) {
        clickObjectByXPath(xPath, null);
    }

    public void clickObjectByXPath(String xPath, String alias) {
        try {
            explicitWait(xPath, 1, alias);
        } catch (TimeoutException e) {
            DotTestListener.log(String.format("\"%s\" not found on page.", (alias != null) ? alias : xPath));
            return;
        }
        findElement(By.xpath(xPath)).click();
        DotTestListener.log(String.format("\"%s\" displayed and was used.", (alias != null) ? alias : xPath));
    }

    public void doubleClickObjectByXPath(String xPath) {
        WaitUtils.waitForMilliseconds();
        waitOneSecondIfElementIsNotPresent(xPath);

        actionDoubleClick(xPath);
    }

    protected void waitOneSecondIfElementIsNotPresent(String xPath) {
        if (!isPresentByXpath(xPath)) WaitUtils.waitForOneSecond();
    }

    protected void actionDoubleClick(String xPath) {
        WebElement e = findElement(By.xpath(xPath));
        Actions dbAction = new Actions(webDriver);
        dbAction.doubleClick(e).build().perform();
        WaitUtils.waitForMilliseconds();
        DotTestListener.logOnly(String.format("\"%s\" displayed and was used.", xPath));
    }

    public boolean isPresentByXpath(String xPath) {
        return isPresentByXpath(xPath, false);
    }

    public boolean isPresentByXpath(String xPath, boolean silent) {
        try {
            Predicate<String> p = x -> findElement(By.xpath(x)).isDisplayed();
            if (!silent) DotTestListener.logOnly(String.format("%s.xPath.%s", p.test(xPath), xPath));
            return p.test(xPath);
        } catch (WebDriverException e) {
            if (!silent) DotTestListener.logOnly("false.xPath." + xPath);
            return false;
        }
    }

    public boolean isPlacedByXpath(String xPath) {
        try {
            Predicate<String> p = x -> findElement(By.xpath(x)).isEnabled();
            DotTestListener.logOnly(String.format("%s.xPath.%s", p.test(xPath), xPath));
            return p.test(xPath);
        } catch (WebDriverException e) {
            DotTestListener.logOnly("false.xPath." + xPath);
            return false;
        }
    }

    public void fillValue(String xPath, String value) {
        fillValue(xPath, value, true);
    }

    public void fillValueByIndex(String xPath, String value, int index) {
        WebElement e = findElements(By.xpath(xPath)).get(index);
        e.clear();
        e.sendKeys(value);
    }

    public void fillValue(String xPath, String value, boolean screen) {
        WebElement e = findElement(By.xpath(xPath));
        e.clear();
        e.sendKeys(value);
        if (screen) {
            takeScreenShot();
        }
    }

    @Override
    public String getAttribute(String xPath, String attrName) {
        WebElement e = findElement(By.xpath(xPath));

        return e.getAttribute(attrName);
    }

    @Override
    public String getAttributeByIndex(String xPath, int index, String attrName) {
        List<WebElement> elemList = findElements(By.xpath(xPath));
        if (elemList == null || elemList.size() < index) return null;

        return elemList.get(index).getAttribute(attrName);
    }

    @Override
    public boolean isEnable(String xPath) {
        WaitUtils.waitForMilliseconds();
        return findElement(By.xpath(xPath)).isEnabled();
    }

    @Override
    public void explicitWait(String xPath) {
        explicitWait(xPath, 200, null);
    }

    public void explicitWait(String xPath, int time) {
        explicitWait(xPath, time, null);
    }

    public void explicitWait(String xPath, String alias) {
        explicitWait(xPath, 200, alias);
    }

    public void explicitWait(String xPath, int time, String alias) {
        DotTestListener.logOnly(String.format("Start Explicit Wait for \"%s\", time to wait: %d seconds.",
                (alias != null) ? alias : xPath, time));
        WebDriverWait wait = new WebDriverWait(webDriver, time);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
    }

    // Create screenShot and add him to List Report using Robot class
    public void takeScreenShot() {
        if (!defaultScreenShotsFolder.exists()) defaultScreenShotsFolder.mkdirs();
        OutputStream outputStream = null;
        String nameFile = "image-" + new DateTime().toString("dd-MM-yyyy_HH-mm-ss") + ".png";
        byte[] scrByte = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        if (scrByte != null) {
            try {
                outputStream = new FileOutputStream(defaultScreenShotsFolder + File.separator + nameFile);
                outputStream.write(scrByte);
            } catch (IOException ignore) {
            } finally {
                try {
                    outputStream.close();
                } catch (IOException | NullPointerException ignore) {
                }
            }
            DotTestListener.log("Screen Shot is created: " + nameFile);

            Reporter.log("<a href=\"" +
                    "images" +
                    File.separator +
                    nameFile +
                    "\"> <img width=\"220\" height=\"160\" border=\"1\" src=\"" +
                    "images" +
                    File.separator +
                    nameFile +
                    "\" alt=\"Screen Shot\"/></a><br />");
        }
    }

    @Override
    public void takeScreenShotUseRobot() {
        throw new NotImplementedException();
    }

    public void sendDownAndPresEnter(String xPath, int n) {
        WebElement cb = webDriver.findElement(By.xpath(xPath));
        Actions buildStep = new Actions(webDriver);
        buildStep.moveToElement(cb, 10, 10);
        while (n > 0) {
            buildStep.sendKeys(Keys.DOWN);
            n--;
        }
        buildStep.sendKeys(Keys.ENTER).release().build().perform();
    }

    @Override
    public void sendKeyPress(Keys key) {
        Actions buildStep = new Actions(webDriver);
        buildStep.sendKeys(key).release().build().perform();
    }

    public void setValueAttribute(String xPath, String tagName, String tagValue) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WaitUtils.waitForOneSecond();
        try {
            WebElement e = findElement(By.xpath(xPath));
            String s = "return arguments[0].setAttribute('" + tagName + "', '" + tagValue + "')";
            js.executeScript(s, e);
            DotTestListener.logOnly("End method setValueAttribute.");
        } catch (NoSuchElementException e) {
            DotTestListener.logOnly("Method setValueAttribute is Failed!");
        }
    }

    public String getValueAttributeUseScript(String xPath, String tagName) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WaitUtils.waitForOneSecond();
        String styleAttribute = "";
        try {
            WebElement e = findElement(By.xpath(xPath));
            String s = "return arguments[0]." + tagName;
            styleAttribute = (String) js.executeScript(s, e);
        } catch (NoSuchElementException e) {
            DotTestListener.logOnly("Does not can get value from " + tagName + " attribute!");
        }
        return styleAttribute;
    }

    // Get text from fields
    public String getTextFromObject(String xPath) {
        WaitUtils.waitForMilliseconds();
        waitOneSecondIfElementIsNotPresent(xPath);
        try {
            return findElement(By.xpath(xPath)).getText();
        } catch (NoSuchElementException e) {
            DotTestListener.logWar("Element by xPath: " + xPath + " does not found!");
            return "";
        }
    }

    // Get text from fields by index
    public String getTextFromObjectByIndex(String xPath, int index) {
        WaitUtils.waitForMilliseconds();
        waitOneSecondIfElementIsNotPresent(xPath);
        return findElements(By.xpath(xPath)).get(index).getText();
    }

    // Get rows from table
    @Override
    public List<WebElement> getSubElementsList(String xPath) {
        WaitUtils.waitForMilliseconds();
        waitOneSecondIfElementIsNotPresent(xPath);
        return findElements(By.xpath(xPath));
    }

    public String getAttributeValue(String xPath, String attributeName) {
        WaitUtils.waitForMilliseconds();
        waitOneSecondIfElementIsNotPresent(xPath);
        return findElement(By.xpath(xPath)).getAttribute(attributeName);
    }

    // Simulate Move Mouse action to object
    public void createMoveAction(String xPath) {
        String mainWindowHandle1 = webDriver.getWindowHandle();
        webDriver.switchTo().window(mainWindowHandle1);
        if (isPresentByXpath(xPath)) {
            WebElement cb = webDriver.findElement(By.xpath(xPath));
            Actions builder = new Actions(webDriver);
            builder.moveToElement(cb, 3, 3).moveByOffset(3, 3).release();
            WaitUtils.waitForOneSecond();
            builder.build().perform();
        }
    }

    public void createMoveAction(WebElement e) {
        Actions builder = new Actions(webDriver);
        builder.moveToElement(e, 10, 10).moveByOffset(10, 10).release();
        WaitUtils.waitForOneSecond();
        builder.build().perform();
    }

    @Override
    public void runJavaScript(String js) {
        getJsExecutor().executeScript(js);
    }

    @Override
    public JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) webDriver;
    }
}
