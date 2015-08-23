package org.highway.whatsup.domain.function;

import org.highway.whatsup.data.physics.BoundsCalculator;
import org.highway.whatsup.data.physics.SpeedMeter;
import org.highway.whatsup.domain.data.ExpressData;

/**
 * Created by engeng on 8/22/15.
 */
public class KoExApiActionCreator {

    private final BoundsCalculator boundsCalculator;
    private final SpeedMeter speedMeter;
    private SpeedMeter.Progression speedProgression;

    public KoExApiActionCreator(BoundsCalculator boundsCalculator, SpeedMeter speedMeter) {
        this.boundsCalculator = boundsCalculator;
        this.speedMeter = speedMeter;
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
