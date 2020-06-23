package com.pages;


import com.configuration.MimesisConfig;
import com.configuration.driver.PageDriver;
import com.google.inject.Inject;


public class WelcomePage implements OpenedInterface {

    public static final String RESULT_AREA = "//*[@id='gt-res-dir-ctr']";
    public static final String PAGE_FLAG = "//div/a[.='Translate']";
    public static final String SOURCE_AREA = "//*[@id='source']";

    @Inject
    private MimesisConfig propertiesLoader;
    @Inject
    private PageDriver pageDriver;

    @Override
    public void open() {
        pageDriver.get(propertiesLoader.mainUrlSelenium());
    }

    @Override
    public boolean isOpen() {
        pageDriver.takeScreenShot();
        return pageDriver.isPresentByXpath(PAGE_FLAG);
    }

    public void putTextToSourceArea(String sourceText) {
        pageDriver.fillValue(SOURCE_AREA, sourceText);
    }

    public String getTextFromResultArea() {
        pageDriver.clickObjectByXPath(RESULT_AREA, "Click by result area");
        pageDriver.explicitWait(RESULT_AREA, 100, "Waiting result is present");
        String textFromObject = pageDriver.getTextFromObject(RESULT_AREA);
        pageDriver.takeScreenShot();
        return textFromObject;
    }
}
