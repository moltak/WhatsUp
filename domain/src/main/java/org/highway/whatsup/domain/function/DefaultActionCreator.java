package org.highway.whatsup.domain.function;

import org.highway.whatsup.data.physics.BoundsCalculator;
import org.highway.whatsup.data.physics.SpeedMeter;
import org.highway.whatsup.data.rest.KoExApiProvider;
import org.highway.whatsup.data.rest.functions.KoExAccidentApi;
import org.highway.whatsup.data.rest.functions.KoExCctvApi;
import org.highway.whatsup.data.rest.functions.KoExEventApi;
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
    private SpeedMeter.Progression speedProgression;

    public DefaultActionCreator(BoundsCalculator boundsCalculator, SpeedMeter speedMeter, KoExApiProvider koExApiProvider) {
        this.boundsCalculator = boundsCalculator;
        this.speedMeter = speedMeter;
        this.koExApiProvider = koExApiProvider;
    }

    public SpeedMeter.Progression getProgression(float speed) {
        speedProgression = speedMeter.getSpeedProgression(speed);
        return speedProgression;
    }

    public ExpressData getExpressWayData(double lat, double lng) {
        if(speedProgression == SpeedMeter.Progression.HIGH_SPEED) {
            return null;
        }

        try {
            return retrieveExpressWayData(lat, lng);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new ExpressData("", "");
    }

    private ExpressData retrieveExpressWayData(double lat, double lng) throws ExecutionException, InterruptedException {
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
                        return new ExpressData(cctv.getDatas().get(0).getCCTVurl(), msg);
                    }
                });
        return o.toBlocking().toFuture().get();
    }

    public ExpressData doit(int speed, double lat, double lng) {
        if(getProgression(speed) == SpeedMeter.Progression.HIGH_SPEED) {
            return null;
        }

        return getExpressWayData(lat, lng);
    }

    public SpeedMeter.Progression getSpeedProgression() {
        return speedProgression;
    }
}
