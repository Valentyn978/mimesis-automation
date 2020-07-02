package com.pages;


import com.configuration.MimesisConfig;
import com.configuration.driver.PageDriver;
import com.google.inject.Inject;


public class NationalWeatherPage implements OpenedInterface {

    public static final String PAGE_FLAG = "//span[.='Today']";
    public static final String TEMP_LABEL = "//section/div/div/span[@data-testid='TemperatureValue']";

    @Inject
    private MimesisConfig config;
    @Inject
    private PageDriver pageDriver;

    @Override
    public void open() {
        pageDriver.get(config.urlWeatherSelenium());
    }

    @Override
    public boolean isOpen() {
        pageDriver.takeScreenShot();
        return pageDriver.isPlacedByXpath(PAGE_FLAG);
    }

    public String getTempToDay() {
        return pageDriver.getTextFromObject(TEMP_LABEL);
    }

}
