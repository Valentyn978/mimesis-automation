package com.helpers;

import com.configuration.reporting.TestLogger;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.testng.TestNGException;

import java.io.*;
import java.net.URLDecoder;

import static com.data.sets.RestClosableClient.closeableClient;
import static java.text.MessageFormat.format;


public class RestSender {

    public static String headerName = "Accept";
    public static String headerData = "text/html";
    public int statusCode;
    private static final Logger LOGGER = TestLogger.getLogger(RestSender.class);
    private String url;
    private String fullUrl;
    private String jSon = "";


    protected RequestHeaders setHeaders = new RequestHeaders();

    public RestSender(String url) {
        this.url = url;
    }

    public CloseableHttpClient setUpHttpClient() {
        return createHttpClient();
    }

    private static CloseableHttpClient createHttpClient() {
        return HttpClientBuilder.create().build();
    }

    public String getResponseByGet(String urlPath) throws TestNGException {
        return getResponse(urlPath, "", "GET");
    }

    public String getResponseByPost(String urlPath, String jSon) {
        return getResponse(urlPath, jSon, "POST");
    }

    public String getResponseByPut(String urlPath, String jSon) {
        return getResponse(urlPath, jSon, "PUT");
    }

    private String getResponse(String urlPath, String jSon, String method) throws TestNGException {
        this.jSon = jSon;
        try {
            if (method.equals("GET")) {
                HttpGet httpGet = setHeaders.setHeaders(urlBuild(urlPath), "GET");
                return getResponseString(getHttpClient().execute(httpGet));
            } else {
                HttpRequestBase request = setHeaders.setHeaders(urlBuild(urlPath), method);
                if (this.jSon != null) {
                    ((HttpEntityEnclosingRequestBase) request).setEntity(new StringEntity(this.jSon, "UTF-8"));
                }
                return getResponseString(getHttpClient().execute(request));
            }
        } catch (Exception e) {
            LOGGER.error(e);
            throw new TestNGException(e);
        }
    }

    private String checkResponseCode(CloseableHttpResponse response) throws IOException, JSONException {
        statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200 && statusCode != 201 && statusCode != 204) {
            String message = buildErrorMessage(response);
            LOGGER.warn(message);
            closeResponse(response);
            return "ErrorMessage: " + message;
        }
        return null;
    }

    private String buildErrorMessage(CloseableHttpResponse response) throws IOException {
        return format("{0} {1}. Request is: {2}; {3}", response.getStatusLine().toString(),
                (response.getEntity() != null ? EntityUtils.toString(response.getEntity(), "UTF-8")
                        : "Response body is empty."),
                String.valueOf(fullUrl), jSon.replaceAll("\",\"", "\", \"")).replaceAll("\"", "'");
    }

    public void closeHttpClient() {
        CloseableHttpClient httpClient = getHttpClient();
        IOUtils.closeQuietly(httpClient);
        try {
            if (httpClient != null)
                httpClient.close();
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    private String urlBuild(String uri) {
        try {
            fullUrl = URLDecoder.decode(String.format("%s%s", url, uri), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e);
        }
        return fullUrl;
    }

    private void closeResponse(CloseableHttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream inStream;
            try {
                inStream = entity.getContent();
                inStream.close();
            } finally {
                response.close();
            }
        }
    }

    private String getResponseString(CloseableHttpResponse response) throws IOException, JSONException {
        HttpEntity entity = response.getEntity();
        String mes = checkResponseCode(response);
        if (mes != null)
            return mes;
        if (entity == null) return null;
        InputStreamReader in = null;
        BufferedReader br = null;
        String output = "";
        try {
            in = new InputStreamReader(entity.getContent(), "UTF-8");
            br = new BufferedReader(in);

            String line;
            while ((line = br.readLine()) != null) {
                output += line;
            }

        } finally {
            if (in != null) in.close();
            if (br != null) br.close();
        }
        closeResponse(response);

        return output;
    }

    protected CloseableHttpClient getHttpClient() {
        return closeableClient;
    }
}
