package com.pages;


import com.configuration.MimesisConfig;
import com.configuration.driver.PageDriver;
import com.google.inject.Inject;


public class JsonPlaceholder implements OpenedInterface {

    @Inject
    private MimesisConfig propertiesLoader;
    @Inject
    private PageDriver pageDriver;

    @Override
    public void open() {
        pageDriver.get(propertiesLoader.mainUrl());
    }

    @Override
    public boolean isOpen() {
        pageDriver.takeScreenShot();
        return pageDriver.isPresentByXpath("//h1[contains(.,'JSONPlaceholder')]");
    }
}
