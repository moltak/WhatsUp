package org.highway.whatsup.domain.actioncreator;

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

    /**
     * @param speed m/s -> speed 가 3이상이면 HIGH_PROGRESSION
     */
    public ExpressData doit(float speed, double lat, double lng) {
        SpeedMeter.Progression progressionSpeed = getProgression(speed);
        if(progressionSpeed == SpeedMeter.Progression.HIGH_SPEED) {
            return new ExpressData(speed, lat, lng, progressionSpeed);
        }

        return getExpressWayData(speed, lat, lng, progressionSpeed);
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

        return new ExpressData(speed, lat, lng, progressionSpeed);
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
                        String cctvUrl = "";
                        ExpressParsedData data = new ExpressParsedData();

                        if (accident.getDataCount() != 0) {
                            data = new ExpressParsedData(accident);
                        }
                        if (event.getDataCount() != 0) {
                            data = new ExpressParsedData(event);
                        }
                        if (cctv.getDataCount() != 0) {
                            cctvUrl = cctv.getDatas().get(0).getCctvUrl();
                        }
                        return new ExpressData(
                                speed, lat, lng, data.occuredLat, data.occuredLng, progressionSpeed,
                                cctvUrl, data.msg, data.eventDirection, data.laneBlockType, data.type);
                    }
                });
        return o.toBlocking().toFuture().get();
    }

    private class ExpressParsedData {

        final String msg, eventDirection;
        final int laneBlockType;
        final double occuredLat, occuredLng;
        final ExpressData.Type type;
        public ExpressParsedData() {
            msg = eventDirection = "";
            laneBlockType = 0;
            occuredLat = occuredLng = 0;
            type = ExpressData.Type.NONE;
        }

        public ExpressParsedData(KoExAccidentApi.Response accident) {
            msg = accident.getData().get(0).getIncidentMsg();
            occuredLat = accident.getData().get(0).getCoordY();
            occuredLng = accident.getData().get(0).getCoordX();
            laneBlockType = parseLaneBlockType(accident.getData().get(0).getLanesBlockType());
            type = ExpressData.Type.ACCIDENT;
            eventDirection = accident.getData().get(0).getEventDirection();
        }

        public ExpressParsedData(KoExEventApi.Response event) {
            msg = event.getData().get(0).getEventStatusMsg();
            occuredLat = event.getData().get(0).getCoordY();
            occuredLng = event.getData().get(0).getCoordX();
            laneBlockType = parseLaneBlockType(event.getData().get(0).getLanesBlockType());
            type = ExpressData.Type.CONSTRUCTION;
            eventDirection = event.getData().get(0).getEventDirection();
        }

        private int parseLaneBlockType(String blockType) {
            try {
                return Integer.parseInt(blockType);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }
}
