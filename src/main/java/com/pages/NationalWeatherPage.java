package com.pages;


import com.configuration.driver.PageDriver;
import com.google.inject.Inject;
import com.helpers.PropertiesLoader;


public class NationalWeatherPage implements OpenedInterface {

    public static final String PAGE_FLAG = "//span[.='Today']";

    @Inject
    private PropertiesLoader propertiesLoader;
    @Inject
    private PageDriver pageDriver;

    @Override
    public void open() {
        pageDriver.get(propertiesLoader.getProperty("URL_WEATHER_SELENIUM"));
    }

    @Override
    public boolean isOpen() {
        pageDriver.takeScreenShot();
        return pageDriver.isPresentByXpath(PAGE_FLAG);
    }

}
