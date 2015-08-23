package org.highway.whatsup.domain.function;

import org.highway.whatsup.data.physics.BoundsCalculator;
import org.highway.whatsup.data.physics.SpeedMeter;
import org.highway.whatsup.data.rest.KoExApiProvider;
import org.highway.whatsup.domain.data.ExpressData;

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

        return retrieveExpressWayData(lat, lng);
    }

    private ExpressData retrieveExpressWayData(double lat, double lng) {
        return new ExpressData("", "");
    }
}
