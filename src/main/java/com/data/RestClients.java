package com.data;


import com.configuration.MimesisConfig;
import org.aeonbits.owner.ConfigFactory;

public interface RestClients {
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
        MimesisConfig mimesisConfig = ConfigFactory.create(MimesisConfig.class);

        return new StringBuilder()
                .append("/data/2.5/weather?").append(mode).append("=")
                .append(nameOrCode)
                .append("&APPID=")
                .append(mimesisConfig.apiId());
    }
}
