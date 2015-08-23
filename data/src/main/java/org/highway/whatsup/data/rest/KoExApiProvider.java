package org.highway.whatsup.data.rest;

import org.highway.whatsup.data.constans.Keys;
import org.highway.whatsup.data.physics.BoundsCalculator;
import org.highway.whatsup.data.rest.adapter.KoExRestAdapter;
import org.highway.whatsup.data.rest.functions.KoExAccidentApi;
import org.highway.whatsup.data.rest.functions.KoExCctvApi;
import org.highway.whatsup.data.rest.functions.KoExEventApi;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by engeng on 8/23/15.
 */
public class KoExApiProvider {

    @Inject KoExApiProvider() {}

    public Observable<KoExEventApi.Response> getEventApi(BoundsCalculator.Bounds bounds) {
        KoExEventApi.Params params = new KoExEventApi.Params(
                Keys.HIGHWAYKEY_API_KEY, "2", "ex",
                bounds.getLng1(), bounds.getLat1(), bounds.getLng2(), bounds.getLat2());

        return KoExRestAdapter.getAdapter()
                .create(KoExEventApi.Request.class)
                .get(params.toMap());
    }

    public Observable<KoExAccidentApi.Response> getAccidentApi(BoundsCalculator.Bounds bounds) {
        KoExAccidentApi.Params params = new KoExAccidentApi.Params(
                Keys.HIGHWAYKEY_API_KEY, "2", "ex",
                bounds.getLng1(), bounds.getLat1(), bounds.getLng2(), bounds.getLat2());

        return KoExRestAdapter.getAdapter()
                .create(KoExAccidentApi.Request.class)
                .get(params.toMap());
    }

    public Observable<KoExCctvApi.Response> getCctvApi(BoundsCalculator.Bounds bounds) {
        KoExCctvApi.Params params = new KoExCctvApi.Params(
                Keys.HIGHWAYKEY_API_KEY, "2", "ex",
                bounds.getLng1(), bounds.getLat1(), bounds.getLng2(), bounds.getLat2());

        return KoExRestAdapter.getAdapter()
                .create(KoExCctvApi.Request.class)
                .get(params.toMap());
    }
}
