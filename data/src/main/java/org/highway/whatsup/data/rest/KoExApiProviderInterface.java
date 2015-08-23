package org.highway.whatsup.data.rest;

import org.highway.whatsup.data.physics.BoundsCalculator;
import org.highway.whatsup.data.rest.functions.KoExAccidentApi;
import org.highway.whatsup.data.rest.functions.KoExCctvApi;
import org.highway.whatsup.data.rest.functions.KoExEventApi;

import rx.Observable;

/**
 * Created by engeng on 8/23/15.
 */
public interface KoExApiProviderInterface {
    Observable<KoExEventApi.Response> getEventApi(BoundsCalculator.Bounds bounds);
    Observable<KoExAccidentApi.Response> getAccidentApi(BoundsCalculator.Bounds bounds);
    Observable<KoExCctvApi.Response> getCctvApi(BoundsCalculator.Bounds bounds);
}
