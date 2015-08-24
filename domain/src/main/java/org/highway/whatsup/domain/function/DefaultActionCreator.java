package org.highway.whatsup.domain.function;

import org.highway.whatsup.data.physics.BoundsCalculator;
import org.highway.whatsup.data.physics.SpeedMeter;
import org.highway.whatsup.data.rest.koex.KoExApiProvider;
import org.highway.whatsup.data.rest.koex.functions.KoExAccidentApi;
import org.highway.whatsup.data.rest.koex.functions.KoExCctvApi;
import org.highway.whatsup.data.rest.koex.functions.KoExEventApi;
import org.highway.whatsup.domain.data.ExpressData;

import java.util.concurrent.ExecutionException;

import rx.Observable;
import rx.functions.Func3;

/**
 * Created by engeng on 8/22/15.
 */
public class DefaultActionCreator {

    private final BoundsCalculator boundsCalculator;
    private final SpeedMeter speedMeter;
    private final KoExApiProvider koExApiProvider;

    public DefaultActionCreator(BoundsCalculator boundsCalculator, SpeedMeter speedMeter, KoExApiProvider koExApiProvider) {
        this.boundsCalculator = boundsCalculator;
        this.speedMeter = speedMeter;
        this.koExApiProvider = koExApiProvider;
    }

    public SpeedMeter.Progression getProgression(float speed) {
        return speedMeter.getSpeedProgression(speed);
    }

    public ExpressData getExpressWayData(
            float speed, double lat, double lng, SpeedMeter.Progression progressionSpeed) {
        try {
            return retrieveExpressWayData(speed, lat, lng, progressionSpeed);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new ExpressData(speed, lat, lng, progressionSpeed, null, null);
    }

    private ExpressData retrieveExpressWayData(
            final float speed, final double lat, final double lng, final SpeedMeter.Progression progressionSpeed)
            throws ExecutionException, InterruptedException {
        BoundsCalculator.Bounds bounds = boundsCalculator.getBounds(lat, lng);
        Observable<ExpressData> o = Observable.zip(
                koExApiProvider.getAccidentApi(bounds),
                koExApiProvider.getEventApi(bounds),
                koExApiProvider.getCctvApi(bounds),
                new Func3<KoExAccidentApi.Response, KoExEventApi.Response, KoExCctvApi.Response, ExpressData>() {
                    @Override
                    public ExpressData call(KoExAccidentApi.Response accident, KoExEventApi.Response event, KoExCctvApi.Response cctv) {
                        String msg = accident.getData().get(0).getExpectedDetourMsg();
                        if (event.getData().get(0).getExpectedDetourMsg() != null) {
                            msg = event.getData().get(0).getExpectedDetourMsg();
                        }
                        return new ExpressData(speed, lat, lng, progressionSpeed, cctv.getDatas().get(0).getCctvUrl(), msg);
                    }
                });
        return o.toBlocking().toFuture().get();
    }

    public ExpressData doit(float speed, double lat, double lng) {
        SpeedMeter.Progression progressionSpeed = getProgression(speed);
        if(progressionSpeed == SpeedMeter.Progression.HIGH_SPEED) {
            return new ExpressData(speed, lat, lng, progressionSpeed, null, null);
        }

        return getExpressWayData(speed, lat, lng, progressionSpeed);
    }
}
