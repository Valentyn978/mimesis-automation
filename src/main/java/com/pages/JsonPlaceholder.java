package com.pages;


import com.configuration.driver.PageDriver;
import com.google.inject.Inject;
import com.helpers.PropertiesLoader;


public class JsonPlaceholder implements OpenedInterface {

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
        pageDriver.takeScreenShot();
        return pageDriver.isPresentByXpath("//h1[contains(.,'JSONPlaceholder')]");
    }
}
