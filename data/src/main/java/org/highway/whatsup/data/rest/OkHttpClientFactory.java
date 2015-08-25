package org.highway.whatsup.data.rest;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.client.Client;
import retrofit.client.OkClient;

/**
 * Created by engeng on 8/22/15.
 */
public class OkHttpClientFactory {
    public static Client getHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(5, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(5, TimeUnit.SECONDS);
        return new OkClient(okHttpClient);
    }
}