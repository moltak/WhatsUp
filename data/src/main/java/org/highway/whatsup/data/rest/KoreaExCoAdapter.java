package org.highway.whatsup.data.rest;

import retrofit.RestAdapter;
import retrofit.converter.SimpleXMLConverter;

/**
 * Created by engeng on 8/22/15.
 */
public class KoreaExCoAdapter {
    public static RestAdapter getAdapter() {
        return new RestAdapter.Builder()
                .setClient(OkHttpClientFactory.getHttpClient())
                .setEndpoint("http://openapi.its.go.kr/api")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new SimpleXMLConverter())
                .build();
    }
}
