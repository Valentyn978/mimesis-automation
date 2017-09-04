package com.helpers;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;
import org.apache.http.client.methods.*;

import static com.helpers.RestSender.headerData;
import static com.helpers.RestSender.headerName;

public class SetRequestHeaders {

	public <T> T setHeaders(String urlToRequest, String type) {
		Header[] headers = getHeaders();
		switch (type) {
			case "GET":
				HttpGet httpGet = new HttpGet(urlToRequest);
				httpGet.setHeaders(headers);
				return (T) httpGet;
			case "POST":
				HttpPost httpPost = new HttpPost(urlToRequest);
				httpPost.setHeaders(headers);
				return (T) httpPost;
			case "PUT":
				HttpPut httpPut = new HttpPut(urlToRequest);
				httpPut.setHeaders(headers);
				return (T) httpPut;
			case "DELETE":
				HttpDelete httpDelete = new HttpDelete(urlToRequest);
				httpDelete.setHeaders(headers);
				return (T) httpDelete;
			case "PATCH":
				HttpPatch httpPatch = new HttpPatch(urlToRequest);
				httpPatch.setHeaders(headers);
				return (T) httpPatch;
			case "OPTIONS":
				HttpOptions httpOptions = new HttpOptions(urlToRequest);
				httpOptions.setHeaders(headers);
				return (T) httpOptions;
			case "HEAD":
				HttpHead httpHead = new HttpHead(urlToRequest);
				httpHead.setHeaders(headers);
				return (T) httpHead;
			default:
				return null;
		}
	}

	public static Header getHeader(String nameH, String valueH) {
		return new Header() {
			@Override
			public String getName() {
				return nameH;
			}

			@Override
			public String getValue() {
				return valueH;
			}

			@Override
			public HeaderElement[] getElements() throws ParseException {
				return new HeaderElement[0];
			}
		};
	}

	protected Header[] getHeaders() {
		return new Header[]{getHeader("Content-Type", "application/json"),
				getHeader("Accept", "application/json"), getHeader(headerName, headerData)};
	}
}
