package com.pages;


import com.configuration.driver.PageDriver;
import com.configuration.driver.PageDriverImpl;
import com.google.inject.Inject;
import com.helpers.PropertiesLoader;


public class WelcomePage implements OpenedInterface {

    @Inject
    private PropertiesLoader propertiesLoader;
    @Inject
    private PageDriver pageDriver;

    @Override
    public void open() {
        pageDriver.get(propertiesLoader.getProperty("MAIN_URL"));
    }

    @Override
    public boolean isOpen() {
        return pageDriver.isPresentByXpath("//div/a[.='Translate']");
    }
}
