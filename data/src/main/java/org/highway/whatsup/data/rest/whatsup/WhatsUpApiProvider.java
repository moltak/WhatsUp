package org.highway.whatsup.data.rest.whatsup;

import org.highway.whatsup.data.entity.whatsup.WhatsupResultEntity;
import org.highway.whatsup.data.rest.whatsup.adapter.WhatsUpRestAdapter;
import org.highway.whatsup.data.rest.whatsup.functions.WhatsupApi;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by engeng on 8/23/15.
 */
public class WhatsUpApiProvider {
    @Inject WhatsUpApiProvider() {}

    public Observable<WhatsupResultEntity> getWhatsupApi(WhatsupApi.Params params) {
        return WhatsUpRestAdapter.getAdapter()
                .create(WhatsupApi.Request.class)
                .whatsup(params.toMap());
    }

    public Observable<WhatsupResultEntity> getStatics(WhatsupApi.Params params) {
        return WhatsUpRestAdapter.getAdapter()
                .create(WhatsupApi.Request.class)
                .statics(params.toMap());
    }
}
