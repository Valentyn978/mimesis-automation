package com.configuration.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface PageDriver extends WebDriver {

    public void clickElementByIndex(String xPath, int n);

    public void clickObjectByXPath(String xPath, String alias);

    public void clickObjectByXPath(String xPath);

    public void fillValue(String xPath, String value);

    public void fillValueByIndex(String xPath, String value, int index);

    public void fillValue(String xPath, String value, boolean screen);

    public String getAttribute(String xPath, String attrName);

    public String getAttributeByIndex(String xPath, int index, String attrName);

    public boolean isPlacedByXpath(String xPath);

    public boolean isPresentByXpath(String xPath);

    public boolean isPresentByXpath(String xPath, boolean silent);

    /**
     * @param xPath Element to wait for.
     * @param time  Seconds until timeout before element appears.
     * @param alias Human-readable name of element represented by xpath.
     */
    public void explicitWait(String xPath, int time, String alias);

    public void explicitWait(String xPath, String alias);

    public void explicitWait(String xPath, int time);

    public void explicitWait(String xPath);

    public void takeScreenShot();

    public void takeScreenShotUseRobot();

    public void sendDownAndPresEnter(String xPath, int n);

    public void sendKeyPress(Keys key);

    public void doubleClickObjectByXPath(String xPath);

    public void setValueAttribute(String xPath, String TagName, String TagValue);

    public String getValueAttributeUseScript(String xPath, String TagName);

    public String getTextFromObject(String xPath);

    public String getTextFromObjectByIndex(String xPath, int index);

    public List<WebElement> getSubElementsList(String xPath);

    public String getAttributeValue(String xPath, String nameAtr);

    public boolean isEnable(String xPath);

    public void createMoveAction(String xPath);

    public void createMoveAction(WebElement e);

    public void runJavaScript(String js);

    public JavascriptExecutor getJsExecutor();
}
