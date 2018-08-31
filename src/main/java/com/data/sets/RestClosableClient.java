package com.data.sets;

import com.configuration.MimesisConfig;
import com.data.RestClients;
import com.helpers.RestSender;
import com.jayway.jsonpath.JsonPath;
import org.aeonbits.owner.ConfigFactory;

public class RestClosableClient implements RestClients {

    private MimesisConfig config = ConfigFactory.create(MimesisConfig.class);


    @Override
    public String getResultByCityName(String name) {
        return Double.toString(JsonPath.read(new RestSender(config.urlApi())
                .getResponseByGet(getRequest(name).toString()), "$." + JSON_PATH));
    }

    @Override
    public String getResultByCityCode(int code) {
        return Double.toString(JsonPath.read(new RestSender(config.urlApi())
                .getResponseByGet(getRequest(code).toString()), "$." + JSON_PATH));
    }
}
