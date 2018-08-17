package com.data.sets;

import com.helpers.RestSender;
import com.jayway.jsonpath.JsonPath;
import org.apache.http.impl.client.CloseableHttpClient;

public class RestClosableClient implements RestClientSelector {

    private final String mainUrl;

    public static CloseableHttpClient closeableClient;


    public RestClosableClient(String mainUrl) {
        this.mainUrl = mainUrl;
        closeableClient = new RestSender("URL_API").setUpHttpClient();
    }

    @Override
    public String getResultByCityName(String name) {
        return Double.toString(JsonPath.read(new RestSender(mainUrl).getResponseByGet(getRequest(name).toString()),
                "$." + JSON_PATH));
    }

    @Override
    public String getResultByCityCode(int code) {
        return Double.toString(JsonPath.read(new RestSender(mainUrl).getResponseByGet(getRequest(code).toString()),
                "$." + JSON_PATH));
    }
}
