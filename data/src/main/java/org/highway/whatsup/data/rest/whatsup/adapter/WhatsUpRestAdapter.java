package org.highway.whatsup.data.rest.whatsup.adapter;

import org.highway.whatsup.data.rest.OkHttpClientFactory;

import retrofit.RestAdapter;

/**
 * Created by engeng on 8/24/15.
 */
public class WhatsUpRestAdapter {
    public static RestAdapter getAdapter() {
        return new RestAdapter.Builder()
                .setClient(OkHttpClientFactory.getHttpClient())
                .setEndpoint("http://diytour.me/")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }
}
