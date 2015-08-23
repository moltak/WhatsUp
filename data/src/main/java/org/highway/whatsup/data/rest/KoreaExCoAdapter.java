package org.highway.whatsup.data.rest;

import retrofit.RestAdapter;

/**
 * Created by engeng on 8/22/15.
 */
public class KoreaExCoAdapter {
    public static RestAdapter getAdapter() {
        return new RestAdapter.Builder()
                .setClient(OkHttpClientFactory.getHttpClient())
                .setEndpoint("http://openapi.its.go.kr/api")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }
}
