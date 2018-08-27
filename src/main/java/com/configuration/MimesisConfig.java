package com.configuration;

import org.aeonbits.owner.Config;

@Config.Sources("file:${user.dir}/MimesisConfig.properties")
public interface MimesisConfig extends Config {
    @Key("testResultFolder")
    @DefaultValue("report")
    String testResultFolder();

    @Key("ChromeDriverPath")
    @DefaultValue("/usr/lib/chromium-browser/chromedriver")
    String chromeDriverPath();

    @Key("GekoDrPath")
    @DefaultValue("/usr/lib/geckodriver")
    String gekoDrPath();

    @Key("TimeOut")
    @DefaultValue("1400")
    int timeOut();

    @Key("TimeWAIT")
    @DefaultValue("120")
    int timeWAIT();

    @Key("MAIN_URL")
    @DefaultValue("https://jsonplaceholder.typicode.com/")
    String mainUrl();

    @Key("MAIN_URL_SELENIUM")
    @DefaultValue("https://translate.google.com/#en")
    String mainUrlSelenium();
}
