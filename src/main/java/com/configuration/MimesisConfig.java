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

    @Key("API_ID")
    @DefaultValue("f31cce7ac5a1983cd83db0da00b2e0e1")
    String apiId();

    @Key("URL_API")
    @DefaultValue("https://api.openweathermap.org")
    String urlApi();

    @Key("URL_WEATHER_SELENIUM")
    @DefaultValue("https://weather.com/weather/today/l/UKXX0085:1:UK")
    String urlWeatherSelenium();
}
