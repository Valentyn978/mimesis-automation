package com.data.sets;

import com.helpers.PropertiesLoader;

public interface RestClientSelector {
    String JSON_PATH = "main.temp";

    String getResultByCityName(String name);

    String getResultByCityCode(int code);

    default StringBuilder getRequest(String cityName) {
        return requestBuild("q", cityName);
    }

    default StringBuilder getRequest(int code) {
        return requestBuild("id", Integer.toString(code));
    }

    static StringBuilder requestBuild(String mode, String nameOrCode) {
        return new StringBuilder()
                .append("/data/2.5/weather?").append(mode).append("=")
                .append(nameOrCode)
                .append("&APPID=")
                .append(new PropertiesLoader().getProperty("API_ID"));
    }
}
