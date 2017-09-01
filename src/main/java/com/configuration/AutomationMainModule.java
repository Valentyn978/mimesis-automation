package com.configuration;

import com.google.inject.AbstractModule;
import com.helpers.PropertiesLoader;

public class AutomationMainModule extends AbstractModule{

    private final PropertiesLoader propertyLoader;

    public AutomationMainModule() {
        this.propertyLoader = new PropertiesLoader();
    }

    @Override
    protected void configure() {
        bind(PropertiesLoader.class).toInstance(propertyLoader);
    }
}
